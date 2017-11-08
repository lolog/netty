package net.netty.plugins.test;

import com.google.protobuf.InvalidProtocolBufferException;

import net.netty.plugins.pojo.User;

public class ProtobufTest {
	byte[] encode(User.UserInfo userInfo) {
		return userInfo.toByteArray();
	}
	User.UserInfo decode (byte[] body) throws InvalidProtocolBufferException {
		return User.UserInfo.parseFrom(body);
	}
	User.UserInfo createUserinfo () {
		User.UserInfo.Builder builder = User.UserInfo.newBuilder();
		
		builder.setName("name");
		builder.setAge(26);
		builder.setInfo("Uway");
		
		return builder.build();
	}
	
	public static void main(String[] args) throws InvalidProtocolBufferException {
		ProtobufTest protobufTest = new ProtobufTest();
		
		User.UserInfo userInfo = protobufTest.createUserinfo();
		System.out.println("useroinfo = " + userInfo.toString());
		System.out.println("[encode] \n" + new String(protobufTest.encode(userInfo)));
		System.out.println("[decode] \n" + protobufTest.decode(protobufTest.encode(userInfo)));
		
	}
}
