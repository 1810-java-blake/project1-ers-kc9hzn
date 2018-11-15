package com.revature.dto;

import com.revature.model.ReimbursementStatus;

public class ReimbursementResolver {
	private int id;
	private ReimbursementStatus status;
	private int userId;
	
	public ReimbursementResolver() {
		super();
	}

	public ReimbursementResolver(int id, ReimbursementStatus status, int userId) {
		super();
		this.id = id;
		this.status = status;
		this.userId = userId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ReimbursementStatus getStatus() {
		return status;
	}

	public void setStatus(ReimbursementStatus status) {
		this.status = status;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + userId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReimbursementResolver other = (ReimbursementResolver) obj;
		if (id != other.id)
			return false;
		if (status != other.status)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ReimbursementResolver [id=" + id + ", status=" + status + ", userId=" + userId + "]";
	}
	
	
}
