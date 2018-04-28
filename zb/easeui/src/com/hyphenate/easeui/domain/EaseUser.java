/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hyphenate.easeui.domain;

import android.os.Parcel;
import android.os.Parcelable;

import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.utils.EaseCommonUtils;

public class EaseUser implements Parcelable {

	public String uid;
	public String userId;
    /**
     * initial letter for nickname
     */
	protected String initialLetter;
	/**
	 * avatar of the user
	 */
	public String avatar;
	public int type=0;//0是个人,1是群组
	public static int TYPE_PERSON=EaseConstant.CHATTYPE_SINGLE;
	public static int TYPE_GROUP= EaseConstant.CHATTYPE_GROUP;
	public String sign;
	public int role; //用户角色 1是家长,2是老师
	public static int ROLE_PARENT=1;
	public static int ROLE_TEACHER=2;
	public String mphone;
	public String username;
	public String nick;
	public int gender = 0;
	public int person = 0;
	public int release = 0;//0是陌生人，1是自己，2是好友
	public String admin="";//针对群组
	public String remark ="";//备注
	public static int RELEASE_UNFRIEND=0;
	public static int RELEASE_MYSELF=1;
	public static int RELEASE_FRIEND=2;
	public String sortKey;
	public EaseUser(String username){
	    this.username = username;
	}

	public String getInitialLetter() {
	    if(initialLetter == null){
            EaseCommonUtils.setUserInitialLetter(this);
        }
		return initialLetter;
	}

	public void setInitialLetter(String initialLetter) {
		this.initialLetter = initialLetter;
	}

	public String getAvatar(){//"http://172.16.46.50:8080"+
		return avatar;
	}

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
	public int hashCode() {
		return 17 * getUsername().hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (o == null || !(o instanceof EaseUser)) {
			return false;
		}
		return getUsername().equals(((EaseUser) o).getUsername());
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public int getRole() {
		return role;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSign() {
		return sign;
	}

	public void setMPhone(String mphone) {
		this.mphone = mphone;
	}

	public String getMPhone() {
		return mphone;
	}


	public String getUsername() {
		return this.username;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getNick() {
		return this.nick == null?this.getUsername():this.nick;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getUid() {
		return uid;
	}


	public int getGender() {
		return gender;
	}
	public String getGenderStr() {
		if(gender==1){
			return "男";
		}else if(gender==2){
			return "女";
		}
		return "未知";
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return nick == null ? username : nick;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public void writeToParcel(Parcel var1, int var2) {
		var1.writeString(this.uid);
		var1.writeString(this.username);
		var1.writeString(this.mphone);
		var1.writeString(this.nick);
		var1.writeString(this.sign);
		var1.writeString(this.avatar);
	    var1.writeString(this.userId);
		var1.writeString(this.admin);
		var1.writeString(this.remark);

		var1.writeInt(this.gender);
		var1.writeInt(this.role);
		var1.writeInt(this.type);
		var1.writeInt(this.person);
		var1.writeInt(this.release);

	}

	private EaseUser(Parcel var1) {
		this.uid = var1.readString();
		this.username = var1.readString();
		this.mphone=var1.readString();
		this.nick = var1.readString();
		this.sign=var1.readString();
		this.avatar = var1.readString();
		this.userId = var1.readString();
		this.admin= var1.readString();
		this.remark = var1.readString();

		this.gender = var1.readInt();
		this.role=var1.readInt();
		this.type=var1.readInt();
		this.person=var1.readInt();
		this.release = var1.readInt();
	}
	public static final Creator<EaseUser> CREATOR = new Creator() {
		public EaseUser createFromParcel(Parcel var1) {
			return new EaseUser(var1);
		}

		public EaseUser[] newArray(int var1) {
			return new EaseUser[var1];
		}
	};

}
