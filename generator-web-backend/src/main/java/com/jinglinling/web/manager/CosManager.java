package com.jinglinling.web.manager;

import cn.hutool.core.collection.CollUtil;
import com.jinglinling.web.config.CosClientConfig;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.exception.MultiObjectDeleteException;
import com.qcloud.cos.model.*;
import com.qcloud.cos.transfer.Download;
import com.qcloud.cos.transfer.TransferManager;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Cos 对象存储操作
 */
@Component
public class CosManager {

    @Resource
    private CosClientConfig cosClientConfig;

    @Resource
    private COSClient cosClient;

    //复用对象
    private TransferManager transferManager;

    //bean加载完成后执行
    //让服务器公用这些线程池，防止重复开启、创建
    @PostConstruct
    public void init() {
        //执行初始化逻辑
        System.out.println("Bean 初始化成功");
        //多线程并发上传下载
        ExecutorService threadPool = Executors.newFixedThreadPool(32);
        transferManager = new TransferManager(cosClient, threadPool);
    }

    /**
     * 上传对象
     *
     * @param key           唯一键
     * @param localFilePath 本地文件路径
     * @return
     */
    public PutObjectResult putObject(String key, String localFilePath) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(cosClientConfig.getBucket(), key,
                new File(localFilePath));
        return cosClient.putObject(putObjectRequest);
    }

    /**
     * 上传对象
     *
     * @param key  唯一键
     * @param file 文件
     * @return
     */
    public PutObjectResult putObject(String key, File file) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(cosClientConfig.getBucket(), key,
                file);
        return cosClient.putObject(putObjectRequest);
    }

    /**
     * 下载对象
     * @param key 对象唯一键
     * @return
     */
    public COSObject getObject(String key) {
        GetObjectRequest getObjectRequest = new GetObjectRequest(cosClientConfig.getBucket(), key);
        return cosClient.getObject(getObjectRequest);
    }

    /**
     * 下载对象到本地文件
     * @param key
     * @param localFilePath 本地文件路径
     * @return
     * @throws InterruptedException
     */
    public Download download(String key, String localFilePath) throws InterruptedException {
        File downloadFile = new File(localFilePath);
        GetObjectRequest getObjectRequest = new GetObjectRequest(cosClientConfig.getBucket(), key);
        Download download = transferManager.download(getObjectRequest, downloadFile);
        //同步等待下载完成
        download.waitForCompletion();
        return download;
    }

    /**
     * 删除对象
     * @param key
     * @throws CosClientException
     * @throws CosServiceException
     */
    public void deleteObject(String key) throws CosClientException, CosServiceException {
        cosClient.deleteObject(cosClientConfig.getBucket(), key);
    }


    /**
     * 批量删除对象
     * @param keyList
     * @return
     * @throws MultiObjectDeleteException
     * @throws CosClientException
     * @throws CosServiceException
     */
    public DeleteObjectsResult deleteObjects(List<String> keyList) throws MultiObjectDeleteException, CosClientException, CosServiceException {
        if (CollUtil.isEmpty(keyList)) {
            return null;
        }
        DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(cosClientConfig.getBucket());
        //设置要删除的key列表，最多一次删除100个
        List<DeleteObjectsRequest.KeyVersion> keyVersions = new ArrayList<>();
        //传入要删除的文件名
        //注意文件名不允许以正斜线"/"或者反斜线"\"开头，例如：
        //存储桶目录下有a/b/c.txt文件，如果要删除，只能是 keyList.add(new KeyVersion("a/b/c.txt")), 若使用 keyList.add(new KeyVersion("/a/b/c.txt"))会导致删除不成功
        for (String key : keyList) {
            keyVersions.add(new DeleteObjectsRequest.KeyVersion(key));
        }
        deleteObjectsRequest.setKeys(keyVersions);
        DeleteObjectsResult deleteObjectsResult = cosClient.deleteObjects(deleteObjectsRequest);
        return deleteObjectsResult;
    }

    /**
     * 删除目录
     * @param delPrefix
     * @throws CosClientException
     * @throws CosServiceException
     */
    public void deleteDir(String delPrefix) throws CosClientException, CosServiceException {
        ListObjectsRequest listObjectsRequest = new ListObjectsRequest();
        //设置bucket名称
        listObjectsRequest.setBucketName(cosClientConfig.getBucket());
        //prefix表示列出的对象名以prefix为前缀
        //这里填要列出目录的相对bucket的路径
        listObjectsRequest.setPrefix(delPrefix);
        //设置最大遍历出多少个对象，一次listObject最大支持1000
        listObjectsRequest.setMaxKeys(1000);
        //保存每次列出的结果
        ObjectListing objectListing = null;

        do {
            objectListing = cosClient.listObjects(listObjectsRequest);
            //这里保存列出的对象列表
            List<COSObjectSummary> cosObjectSummaries = objectListing.getObjectSummaries();
            if (CollUtil.isEmpty(cosObjectSummaries)) {
                break;
            }

            List<DeleteObjectsRequest.KeyVersion> delObjects = new ArrayList<>();
            for (COSObjectSummary cosObjectSummary : cosObjectSummaries) {
                delObjects.add(new DeleteObjectsRequest.KeyVersion(cosObjectSummary.getKey()));
            }

            DeleteObjectsRequest deleteObjectsRequest = new DeleteObjectsRequest(cosClientConfig.getBucket());
            deleteObjectsRequest.setKeys(delObjects);
            cosClient.deleteObjects(deleteObjectsRequest);

            //标记下一次开始的位置
            String nextMarker = objectListing.getNextMarker();
            listObjectsRequest.setMarker(nextMarker);
        } while (objectListing.isTruncated());
    }
}
