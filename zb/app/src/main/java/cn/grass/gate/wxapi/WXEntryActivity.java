package cn.grass.gate.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Button;

import com.tencent.mm.sdk.openapi.BaseReq;
import com.tencent.mm.sdk.openapi.BaseResp;
import com.tencent.mm.sdk.openapi.ConstantsAPI;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.ShowMessageFromWX;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.tencent.mm.sdk.openapi.WXAppExtendObject;
import com.tencent.mm.sdk.openapi.WXMediaMessage;
import com.weedys.weedlibrary.qq.AppConstants;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler{
	
	private static final int TIMELINE_SUPPORTED_VERSION = 0x21020001;
	
	private Button gotoBtn, regBtn, launchBtn, checkBtn;
	

    private IWXAPI api;
	private String  APP_ID="wx83df49b449f4a01a";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.entry);

    	api = WXAPIFactory.createWXAPI(this, APP_ID, false);

//    	regBtn = (Button) findViewById(R.id.reg_btn);
//    	regBtn.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
				// ����appע�ᵽ΢��
			    api.registerApp(APP_ID);
		//	}
		//});
    	
//        gotoBtn = (Button) findViewById(R.id.goto_send_btn);
//        gotoBtn.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//		        startActivity(new Intent(WXEntryActivity.this, SendToWXActivity.class));
//		        finish();
//			}
//		});
//
//        launchBtn = (Button) findViewById(R.id.launch_wx_btn);
//        launchBtn.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				Toast.makeText(WXEntryActivity.this, "launch result = " + api.openWXApp(), Toast.LENGTH_LONG).show();
//			}
//		});
        
//        checkBtn = (Button) findViewById(R.id.check_timeline_supported_btn);
//        checkBtn.setOnClickListener(new View.OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				int wxSdkVersion = api.getWXAppSupportAPI();
//				if (wxSdkVersion >= TIMELINE_SUPPORTED_VERSION) {
//					Toast.makeText(WXEntryActivity.this, "wxSdkVersion = " + Integer.toHexString(wxSdkVersion) + "\ntimeline supported", Toast.LENGTH_LONG).show();
//				} else {
//					Toast.makeText(WXEntryActivity.this, "wxSdkVersion = " + Integer.toHexString(wxSdkVersion) + "\ntimeline not supported", Toast.LENGTH_LONG).show();
//				}
//			}
//		});
        
        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
		switch (req.getType()) {
		case ConstantsAPI.COMMAND_GETMESSAGE_FROM_WX:
			finish();
		//	goToGetMsg();
			break;
		case ConstantsAPI.COMMAND_SHOWMESSAGE_FROM_WX:
		//	goToShowMsg((ShowMessageFromWX.Req) req);
			break;
		default:
			break;
		}
	}

	Intent intent;
	@Override
	public void onResp(BaseResp resp) {
		switch (resp.errCode) {
		case BaseResp.ErrCode.ERR_OK:
			intent = new Intent();

			intent.putExtra("errCode", resp.errCode);
			if(resp.getType()==ConstantsAPI.COMMAND_SENDAUTH){
				intent.setAction(AppConstants.ACTION_LOGIN_WX);
				Bundle bundle = new Bundle();
				resp.toBundle(bundle);
				String code = bundle.getString("_wxapi_sendauth_resp_token");
				intent.putExtra("code", code);
				LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
				lbm.sendBroadcast(intent);
			}else if(resp.getType()==ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX){
				intent.setAction(AppConstants.ACTION_SHARE_WX);
				LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
				lbm.sendBroadcast(intent);
			}

			break;
		case BaseResp.ErrCode.ERR_USER_CANCEL:
		case BaseResp.ErrCode.ERR_AUTH_DENIED:
			default:
			intent = new Intent();
			intent.putExtra("errCode", resp.errCode);
			if(resp.getType()==ConstantsAPI.COMMAND_SENDAUTH){
				intent.setAction(AppConstants.ACTION_LOGIN_WX);
				LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
				lbm.sendBroadcast(intent);
			}else if(resp.getType()==ConstantsAPI.COMMAND_SENDMESSAGE_TO_WX){
				intent.setAction(AppConstants.ACTION_SHARE_WX);
				LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
				lbm.sendBroadcast(intent);
			}
			break;
		}
		finish();
		//Toast.makeText(this, ""+result, Toast.LENGTH_LONG).show();
	}
//
//	private void goToGetMsg() {
//		Intent intent = new Intent(this, GetFromWXActivity.class);
//		intent.putExtras(getIntent());
//		startActivity(intent);
//		finish();
//	}
	
	private void goToShowMsg(ShowMessageFromWX.Req showReq) {
		WXMediaMessage wxMsg = showReq.message;		
		WXAppExtendObject obj = (WXAppExtendObject) wxMsg.mediaObject;
		
		StringBuffer msg = new StringBuffer(); // ��֯һ������ʾ����Ϣ����
		msg.append("description: ");
		msg.append(wxMsg.description);
		msg.append("\n");
		msg.append("extInfo: ");
		msg.append(obj.extInfo);
		msg.append("\n");
		msg.append("filePath: ");
		msg.append(obj.filePath);
		
//		Intent intent = new Intent(this, ShowFromWXActivity.class);
//		intent.putExtra(Constants.ShowMsgActivity.STitle, wxMsg.title);
//		intent.putExtra(Constants.ShowMsgActivity.SMessage, msg.toString());
//		intent.putExtra(Constants.ShowMsgActivity.BAThumbData, wxMsg.thumbData);
//		startActivity(intent);
		finish();
	}
}