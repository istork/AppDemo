package com.wotingfm.activity.person.uploadavatar.activity;

import java.io.File;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shenstec.utils.ShensUtils;
import com.shenstec.utils.file.FileManager;
import com.wotingfm.R;
import com.wotingfm.activity.person.uploadavatar.model.ImageInfo;
import com.wotingfm.activity.person.uploadavatar.model.userPortait;
import com.wotingfm.main.common.StringConstant;
import com.wotingfm.main.commonactivity.GlobalConfig;
import com.wotingfm.main.http.MyHttp;
import com.wotingfm.utils.ImageLoader;
import com.wotingfm.utils.PhoneMessage;
import com.wotingfm.utils.ToastUtil;
import com.wotingfm.utils.Utils;

public class TxActivity extends Activity implements OnClickListener {

	private LinearLayout head_left_btn;
	private LinearLayout lin_foot;
	private LinearLayout lin_qx;
	private TextView tv_xiangce;
	private TextView tv_paizhao;
	private LinearLayout lin_other;
	private LinearLayout lin_chose;
	private TxActivity context;
	private Animation mAnimation;
	private final int TO_GALLERY = 1;
	private final int TO_CAMARA = 2;
	private final int PHOTO_REQUEST_CUT = 3;
	private Uri outputFileUri;
	private String outputFilePath;
	private String imagePath;
	private Dialog dialog;
	private ImageView TximageView;
	private ImageLoader imgloader;
	private String BigImageUrl;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_tx);
		context = this;
		setview();//设置界面
		setlistener();//设置监听
	}

	private void setview() {
		// TODO Auto-generated method stub
		head_left_btn = (LinearLayout) findViewById(R.id.head_left_btn);
		TximageView = (ImageView) findViewById(R.id.imageViewtx);
		lin_foot = (LinearLayout) findViewById(R.id.lin_foot);
		lin_qx = (LinearLayout) findViewById(R.id.lin_qx);
		tv_xiangce = (TextView) findViewById(R.id.tv_xiangce);
		tv_paizhao = (TextView) findViewById(R.id.tv_paizhao);
		lin_other = (LinearLayout) findViewById(R.id.lin_other);
		lin_chose = (LinearLayout) findViewById(R.id.lin_chose);
	}

	private void setlistener() {
		// TODO Auto-generated method stub
		lin_other.setOnClickListener(this);
		tv_paizhao.setOnClickListener(this);
		tv_xiangce.setOnClickListener(this);
		lin_qx.setOnClickListener(this);
		lin_foot.setOnClickListener(this);
		TximageView.setOnClickListener(this);
		head_left_btn.setOnClickListener(this);
		// 注册ImageLoader
		imgloader = new ImageLoader(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.head_left_btn:
			finish();
			break;
		case R.id.imageViewtx:
			ToastUtil.show_short(context, "看大图");
			break;
		case R.id.lin_foot:
			mAnimation = AnimationUtils.loadAnimation(context,
					R.anim.umeng_socialize_slide_in_from_bottom);
			lin_chose.setAnimation(mAnimation);
			lin_chose.setVisibility(View.VISIBLE);
			break;
		case R.id.tv_xiangce:
			mAnimation = AnimationUtils.loadAnimation(context,
					R.anim.umeng_socialize_slide_out_from_bottom);
			lin_chose.setAnimation(mAnimation);
			lin_chose.setVisibility(View.GONE);
			// 调用图库
			Intent intent = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			intent.setType("image/*");
			startActivityForResult(intent, TO_GALLERY);
			break;
		case R.id.tv_paizhao:
			mAnimation = AnimationUtils.loadAnimation(context,
					R.anim.umeng_socialize_slide_out_from_bottom);
			lin_chose.setAnimation(mAnimation);
			lin_chose.setVisibility(View.GONE);
			String savepath = FileManager.getImageSaveFilePath(context);
			FileManager.createDirectory(savepath);
			String fileName = System.currentTimeMillis() + ".jpg";
			File file = new File(savepath, fileName);
			outputFileUri = Uri.fromFile(file);
			outputFilePath = file.getAbsolutePath();
			// 调用照相机
			Intent intenta = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intenta.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
			startActivityForResult(intenta, TO_CAMARA);
			break;
		case R.id.lin_other:
			mAnimation = AnimationUtils.loadAnimation(context,
					R.anim.umeng_socialize_slide_out_from_bottom);
			lin_chose.setAnimation(mAnimation);
			lin_chose.setVisibility(View.GONE);
			break;
		case R.id.lin_qx:
			mAnimation = AnimationUtils.loadAnimation(context,
					R.anim.umeng_socialize_slide_out_from_bottom);
			lin_chose.setAnimation(mAnimation);
			lin_chose.setVisibility(View.GONE);
			break;
		default:
			break;
		}
	}

	// 弹出的第二个界面销毁时 调用此函数
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case TO_GALLERY:
			if (resultCode == Activity.RESULT_OK) {
				if (data != null) {
					ImageInfo imageInfo = new ImageInfo();
					// 取得返回的Uri,基本上选择照片的时候返回的是以Uri形式，但是在拍照中有得机子呢Uri是空的，所以要特别注意
					Uri mImageCaptureUri = data.getData();
					// 返回的Uri不为空时，那么图片信息数据都会在Uri中获得。如果为空，那么我们就进行下面的方式获取
					if (mImageCaptureUri != null) {
						String[] proj = { MediaStore.Images.Media.DATA };
						Cursor cursor = context.managedQuery(mImageCaptureUri,
								proj, null, null, null);
						// 按我个人理解 这个是获得用户选择的图片的索引值
						int column_index = cursor
								.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
						cursor.moveToFirst();
						// 最后根据索引值获取图片路径
						String path = cursor.getString(column_index);
						imagePath = path;
						// Bitmap bitmap=Utils.decodeFile(new File(path));
						//
						// // Bitmap bitmap=ImageUtils.readBitMap(path);
						// try {
						// imagePath=Utils.saveBitmap(PersonNewsActivity.this,
						// bitmap);
						// } catch (Exception e) {
						// // TODO Auto-generated catch block
						// e.printStackTrace();
						// }
						mImageCaptureUri = Uri.fromFile(new File(path));
						startPhotoZoom(mImageCaptureUri);
					}
				}
			}
			break;
		case TO_CAMARA:
			if (resultCode == Activity.RESULT_OK) {
				//
				ImageInfo imageInfo = new ImageInfo();
				Uri mImageCaptureUri = Uri.fromFile(new File(outputFilePath));
				imagePath = outputFilePath;
				startPhotoZoom(mImageCaptureUri);
			}
			break;
		case PHOTO_REQUEST_CUT:
			if (resultCode == Activity.RESULT_OK) {
				dialog = Utils.Dialogph(context, "提交中", dialog);
				chuli();
			}
			break;
		default:
			break;
		}
	}

	private void chuli() {
		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				// TODO Auto-generated method stub
				super.handleMessage(msg);
				if (msg.what == 1) {
					ToastUtil.show_short(context, "保存成功");
					 SharedPreferences sp =getSharedPreferences("wotingfm", Context.MODE_PRIVATE); 
					 Editor et = sp.edit();
					 et.putString(StringConstant.ImageUrl, BigImageUrl);
					 et.commit(); 
					 Log.e("", BigImageUrl); 
					imgloader.DisplayImage(BigImageUrl.replace("\\/", "/"), TximageView, false, true,
							null, null);
				/*	TximageView.setImageURI(Uri.parse(imageurl));*/
					if (dialog != null) {
						dialog.dismiss();
					}
				} else if (msg.what == 0) {
					if (dialog != null) {
						dialog.dismiss();
					}
					ShensUtils.showDialog(context, "提示", "头像保存失败，请稍后再试", false);
				} else if (msg.what == -1) {
					if (dialog != null) {
						dialog.dismiss();
					}
					ShensUtils.showDialog(context, "提示",
							"头像保存异常，图片未上传成功，请重新发布", false);
				}
			}
		};

		new Thread() {
			private String ReturnType;
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run(); //
				Message msg = new Message();
				try {
					Bitmap bitmap = Utils.decodeFile(new File(imagePath));
					String filePath = Utils.saveBitmap(context, bitmap);
					String ExtName = filePath.substring(filePath
							.lastIndexOf("."));
					Log.i("图片", "地址" + filePath);
					// http协议
					String Response = MyHttp
							.postFile(
									new File(filePath),
									GlobalConfig.baseUrl
											+ "wt/passport/uploadImg.do?PType=Portrait&ExtName="
											+ ExtName + "&SessionId="
											+ Utils.getSessionId(context)
											+ "&UserId="
											+ Utils.getUserId(context)
											+ "&IMEI=" + PhoneMessage.imei);
					/*
					 * +"&IMEI="+PhoneMessage.imei+"&ScreenSize="+PhoneMessage.
					 * ScreenWidth+"x"+PhoneMessage.ScreenHeight
					 * +"&GPS="+PhoneMessage
					 * .getGps(TxActivity.this)+"&Machine="+
					 * PhoneMessage.productor
					 * +"&MobileType="+PhoneMessage.model);
					 */
					System.out.println("jsonString = " + Response);
					/*
					 * String Response=
					 * "{'SessionId':'4489xxxxxx','ImageUrl':'/storage/sdcard1//woting//image/1446789856023.jpg','ReturnType':'1001'}"
					 * ;
					 */
					if (Response == null || Response.equals("")) {
						msg.what = 0;
					} else {
						userPortait userp = new userPortait();
						Gson gson = new Gson();
						userp = gson.fromJson(Response,
								new TypeToken<userPortait>() {
								}.getType());
						try {
							ReturnType = userp.getReturnType();
						} catch (Exception e) {

						}
						try {
							String SessionId = userp.getSessionId();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						try {
							  BigImageUrl = GlobalConfig.baseUrl+userp.getBigUrl();
						} catch (Exception e) {
							e.printStackTrace();
						}
						try {
							String SmallImageUrl = userp.getMiniUri();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						if (ReturnType.equals("1001")) {
							msg.what = 1;
						} else {
							msg.what = 0;
						}
					}
				} catch (Exception e) {
					// 异常处理
					e.printStackTrace();
					if (e != null && e.getMessage() != null) {
						msg.obj = "异常" + e.getMessage().toString();
						Log.e("异常1", "" + e.getMessage());
					} else {
						Log.e("异常", "" + e);
						msg.obj = "异常";
					}
					msg.what = -1;
				}
				handler.sendMessage(msg);
			}
		}.start();
	}

/*
 * 图片裁剪
 */
	private void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// crop为true是设置在开启的intent中设置显示的view可以剪裁
		intent.putExtra("crop", "true");
		// // aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// // outputX,outputY 是剪裁图片的宽高
		// intent.putExtra("outputX", size);
		// intent.putExtra("outputY", size);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
		intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
		intent.putExtra("noFaceDetection", true); // 人脸识别
		 intent.putExtra("return-data", true);
		startActivityForResult(intent, PHOTO_REQUEST_CUT);
	}
}