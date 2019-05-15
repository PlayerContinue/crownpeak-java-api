package org.crownpeak.api;


public final class Util {
	
	public final static int maxFailRetries = 5;
	public final static int initialWait = 3;
	public enum AssetTypes{
		NONE (0),
		FILE (2),
		FOLDER (4),
		MOUNT(9),
		CONNECTOR(10);
		
		private int assetType;
		
		public int value() {
			return this.assetType;
		}
		
		private AssetTypes(int assetType){
			this.assetType = assetType;
		}
	}
	
	public enum StatusCode{
		TIMEOUT (429),
		SUCCESS (200);
		
		private int statusCode;
		
		
		
		public int value() {
			return this.statusCode;
		}
		
		private StatusCode(int internalValue){
			this.statusCode = internalValue;
		}
	}
	
	public enum ResultCode{
		SUCCESS ("conWS_Success");
		
		private String resultCode;
		public String value() {
			return this.resultCode;
		}
		
		private ResultCode(String resultCode) {
			this.resultCode = resultCode;
		}
	}
	
}
