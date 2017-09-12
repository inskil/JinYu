package com.example.jinyu.Download;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import android.webkit.MimeTypeMap;

import com.example.jinyu.Init;

/**
 * Created by yyx on 2017/8/12.
 */

public class Download {
    public void download(Context context,String downloadUrl,String fileName,Init init)throws Exception
    {


        BroadcastReceiver broadcastReceiver;
        final Init initialer = init;

        Log.d("download enter",fileName);

        //创建下载任务,downloadUrl就是下载链接
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(downloadUrl));
        //设置文件类型，可以在下载结束后自动打开该文件
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String mimeString = mimeTypeMap.getMimeTypeFromExtension(MimeTypeMap.getFileExtensionFromUrl(downloadUrl));
        request.setMimeType(mimeString);

        //在通知栏中显示，默认就是显示的
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN);
        request.setVisibleInDownloadsUi(false);

//指定下载路径和下载文件名
        request.setDestinationInExternalPublicDir(initialer.getRootPath(), fileName);
//获取下载管理器
        DownloadManager downloadManager= (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
//将下载任务加入下载队列，否则不会进行下载
        final long Id = downloadManager.enqueue(request);

        // 注册广播监听系统的下载完成事件。
        IntentFilter intentFilter = new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                DownloadManager manager = (DownloadManager)context.getSystemService(Context.DOWNLOAD_SERVICE);
                if(DownloadManager.ACTION_DOWNLOAD_COMPLETE.equals(intent.getAction())){
                    DownloadManager.Query query = new DownloadManager.Query();
                    long ID = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                    query.setFilterById(ID);
                    Cursor c = manager.query(query);
                    if(c.moveToFirst()) {
                        String filename = c.getString(c.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI));
                        String[] path = filename.split("/");
                        filename = path[path.length-1];
                        Log.d("download filename",filename);

                        if(filename.equals(initialer.getWordSource())){
                            initialer.load_wordDb();
                        }
                        else if(filename.equals(initialer.getSentenceSource())){
                            initialer.load_sentenceDb();
                        }
                        else {
                            Log.d("download error","filename error");
                        }
                    }
                }

            }
        };

        context.registerReceiver(broadcastReceiver, intentFilter);
    }
}
