package com.cgcl.beans;

import java.util.Date;

import lombok.Data;

@Data
public class PsxRequestBean {
    private String requestId;
	private String custunqid;
	private Date enginepickuptime;
	private Date engineprocessedtime;
	private Date inserttime;
	private Date lchgtime;
	private Integer matchcount;
	private Integer offlinematchcount;
	private Integer onlinematchcount;
	private String processdurationmillis;
	private String profileid;
	private String psxbatchid;
	private String queuewaitingtimemillis;
	private String requeststatus;
	private String userid;
	private String assignUcic;
	private Integer negativeMatchCount;
	private Integer negativeOfflineMatchCount;
	private String assignedUcicNo;

}
