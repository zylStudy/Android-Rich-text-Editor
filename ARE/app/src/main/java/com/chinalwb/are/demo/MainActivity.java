package com.chinalwb.are.demo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.chinalwb.are.AREditor;
import com.chinalwb.are.Util;
import com.chinalwb.are.models.AtItem;
import com.chinalwb.are.strategies.AtStrategy;
import com.chinalwb.are.strategies.VideoStrategy;
import com.chinalwb.are.styles.toolbar.ARE_Toolbar;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.chinalwb.are.demo.TextViewActivity.HTML_TEXT;

/**
 * All Rights Reserved.
 *
 * @author Wenbin Liu
 *
 */
public class MainActivity extends AppCompatActivity {

    private static final int REQ_WRITE_EXTERNAL_STORAGE = 10000;

    private AREditor arEditor;

    private AtStrategy mAtStrategy = new AtStrategy() {
        @Override
        public void openAtPage() {
            Intent intent = new Intent(MainActivity.this, AtActivity.class);
            startActivityForResult(intent, ARE_Toolbar.REQ_AT);
        }

        @Override
        public boolean onItemSelected(AtItem item) {
            return false;
        }
    };

    private VideoStrategy mVideoStrategy = new VideoStrategy() {
        @Override
        public String uploadVideo(Uri uri) {
            try {
                Thread.sleep(3000); // Do upload here
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "http://www.xx.com/x.mp4";
        }

        @Override
        public String uploadVideo(String videoPath) {
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return "http://www.xx.com/x.mp4";
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        this.arEditor = this.findViewById(R.id.areditor);
//        this.arEditor.setAtStrategy(mAtStrategy);
        this.arEditor.setVideoStrategy(mVideoStrategy);
        String html = "<html><body><p><b>aaaa</b></p><p><i>bbbb</i></p>\n" +
                "    <p><u>cccc</u></p>\n" +
                "    <p><span style=\"text-decoration:line-through;\">dddd</span></p>\n" +
                "    <p style=\"text-align:start;\">Alignleft</p>\n" +
                "    <p style=\"text-align:center;\">Align center</p>\n" +
                "    <p style=\"text-align:end;\">Align right</p>\n" +
                "    <p style=\"text-align:start;\">Align left</p>\n" +
                "    <p style=\"text-align:start;\">Hello left<span style=\"background-color:#FFFF00;\"> good?</span> yes</p>\n" +
                "    <p style=\"text-align:start;\">Text color <span style=\"color:#FF5722;\">red </span><span style=\"color:#4CAF50;\">green </span><span style=\"color:#2196F3;\">blue </span><span style=\"color:#9C27B0;\">purple</span><span style=\"color:#000000;\"> normal black</span></p>\n" +
                "    <br>\n" +
                "    <p style=\"text-align:start;\">Click to open <a href=\"http://www.qq.com\">QQ</a> website</p>\n" +
                "    <br><br>\n" +
                "    <blockquote><p style=\"text-align:start;\">Quote</p>\n" +
                "    <p style=\"text-align:start;\">Quote 2nd line</p>\n" +
                "    <br>\n" +
                "    </blockquote>\n" +
                "    <br><br>\n" +
                "    <p style=\"text-align:start;\">2X<sub>1</sub><sup>2 </sup>+3X<sub>1</sub><sup>2</sup>=5X<sub>1</sub><sup>2</sup></p>\n" +
                "    <br>\n" +
                "    <br>\n" +
                "    <p style=\"text-align:start;\"><hr /> </p>\n" +
                "    <p style=\"text-align:start;\">Text <span style=\"font-size:32px\";>SIZE </span><span style=\"font-size:18px\";><span style=\"font-size:21px\";>normal</span></span></p>\n" +
                "    <br>\n" +
                "    <p style=\"text-align:center;\"><img src=\"emoji|" + R.drawable.wx_d_8 + "\"></p>\n" +
                "    <p style=\"text-align:start;\">Image:</p>\n" +
                "    <p style=\"text-align:start;\"><img src=\"http://d.hiphotos.baidu.com/image/pic/item/6159252dd42a2834171827b357b5c9ea14cebfcf.jpg\" /></p>\n" +
                "    <p style=\"text-align:start;\"></p>\n" +
                "    <p><a href=\"#\" ukey=\"2131230814\" uname=\"Steve Jobs\" style=\"color:#FF00FF;\">@Steve Jobs</a>, <a href=\"#\" ukey=\"2131230815\" uname=\"Bill Gates\" style=\"color:#0000FF;\">@Bill Gates</a>, how are you?</p>" +
                "    <p style=\"text-align:start;\"><emoji src=\"2131230915\" /><emoji src=\"2131230936\" /><emoji src=\"2131230929\" /></p>" +
                "    <ul>" +
                "    <li>aa</li>" +
                "    <li>bb</li>\n" +
                "    <li>dd</li>\n" +
                "    <li>eea</li>\n" +
                "    </ul>" +
                "    <ol>\n" +
                "    <li>ddasdf</li>\n" +
                "    <li>sdf</li>\n" +
                "    <li>cc</li>\n" +
                "    </ol>" +
                "    <p style=\"text-align:center;\"><video src=\"http://www.xx.com/x.mp4\" uri=\"/storage/emulated/0/Download/wx_camera_1519181163870 (1).mp4\" controls=\"controls\"></video></p>" +
                "    <p style=\"text-align:start;\"><img src=\"http://a.hiphotos.baidu.com/image/h%3D300/sign=13dc7fee3512b31bd86ccb29b6193674/730e0cf3d7ca7bcb6a172486b2096b63f624a82f.jpg\" /></p>" +
                "    </body></html>";
        this.arEditor.fromHtml(html);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(com.chinalwb.are.R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuId = item.getItemId();
        if (menuId == com.chinalwb.are.R.id.action_save) {
            String html = this.arEditor.getHtml();
            saveHtml(html);
            return true;
        }
        if (menuId == R.id.action_show_tv) {
            String html = this.arEditor.getHtml();
            Intent intent = new Intent(this, TextViewActivity.class);
            intent.putExtra(HTML_TEXT, html);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("SimpleDateFormat") private void saveHtml(String html) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                    && this.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                //申请授权
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, ARE_Toolbar.REQ_VIDEO);
                return;
            }

            String filePath = Environment.getExternalStorageDirectory() + File.separator + "ARE" + File.separator;
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdir();
            }

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh_mm_ss");
            String time = dateFormat.format(new Date());
            String fileName = time.concat(".html");

            File file = new File(filePath + fileName);
            if (!file.exists()) {
                boolean isCreated = file.createNewFile();
                if (!isCreated) {
                    Util.toast(this, "Cannot create file at: " + filePath);
                    return;
                }
            }

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(html);
            fileWriter.close();

            Util.toast(this, fileName + " has been saved at " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
            Util.toast(this, "Run into error: " + e.getMessage());
        }
    }


    /**
     * The layout file.
     */
    private int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_WRITE_EXTERNAL_STORAGE) {
            String html = this.arEditor.getHtml();
            saveHtml(html);
            return;
        }
        this.arEditor.onActivityResult(requestCode, resultCode, data);
    }
}
