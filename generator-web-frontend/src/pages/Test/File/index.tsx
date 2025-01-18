import { COS_HOST } from '@/constants';
import { testDownloadFileUsingGet, testUploadFileUsingPost } from '@/services/backend/fileController';
import { InboxOutlined } from '@ant-design/icons';
import { Button, Card, Divider, Flex, message, Upload, UploadProps } from 'antd';
import { saveAs } from 'file-saver';
import React, { useState } from 'react';

const { Dragger } = Upload;


//文件上传下载测试页面
const TestFilePage: React.FC = () => {
  const [value, setValue] = useState<string>();

  const props: UploadProps = {
    name: 'file',
    multiple: false,
    maxCount: 1,
    customRequest: async (fileObj: any) => {
      try {
        const res = await testUploadFileUsingPost({},fileObj.file);
        fileObj.onSuccess(res.data);
        setValue(res.data);
      } catch (e: any) {
        message.error('上传失败，' + e.message);
        fileObj.onError(e);
      }
    },
    onRemove() {
      setValue(undefined);
    }
  };

  return (
    <Flex gap={16}>
      <Card title="文件上传">
        <Dragger {...props}>
          <p className='ant-upload-drag-icon'>
            <InboxOutlined />
          </p>
          <p className='ant-upload-text'>单击或拖动文件到此区域以上传</p>
          <p className='ant-upload-hint'>
            支持单次上传。严禁上传公司数据或其他被禁止的文件。
          </p>
        </Dragger>
      </Card>
      <Card title="文件下载" loading={!value}>
        <div>文件地址：{COS_HOST + value}</div>
        <Divider />
        <img src={COS_HOST + value} height={280} />
        <Divider />
        <Button
          onClick={async () => {
            const blob = await testDownloadFileUsingGet(
              {
                filepath: value,
              },
              {
                responseType: 'blob',
              },
            );
            // 使用 file-saver来保存文件
            const fullPath = COS_HOST + value;
            saveAs(blob,fullPath.substring(fullPath.lastIndexOf('/') + 1));
          }}
        >
          点击下载文件
        </Button>
      </Card>
    </Flex>
  );
};

export default TestFilePage;
