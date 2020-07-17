package com.retailsbs.logistikapp.user.dto;

import com.retailsbs.logistikapp.user.domain.Attachment;

public class AttachmentDTO  extends Attachment{
	private byte[] in;
	private String folder;
	
	public byte[] getIn() {
		return in;
	}

	public void setIn(byte[] in) {
		this.in = in;
	}

	public String getFolder() {
		return folder;
	}

	public void setFolder(String folder) {
		this.folder = folder;
	}

}
