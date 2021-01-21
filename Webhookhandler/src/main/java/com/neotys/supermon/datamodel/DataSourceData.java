package com.neotys.supermon.datamodel;

import java.util.List;
import java.util.Map;

public class DataSourceData {

	//	{
	//        "dataSourceId": 2,
	//        "databaseName": "easypay",
	//        "schemaName": "easypay",
	//        "hostUrl": "localhost",
	//        "data": {
	//            "STATEMENTS": 221160.0,
	//            "STATEMENT_LATENCY_IN_S": 2153.86,
	//            "FILE_IO_LATENCY_IN_S": 1540.15,
	//            "CURRENT_CONNECTIONS": 14.0,
	//            "DATABASE_SIZE_IN_MB": 0.1,
	//            "STATEMENT_AVG_LATENCY_IN_MS": 9.74,
	//            "APPLICATION_ID": 1.0,
	//            "FILE_IOS": 305867.0,
	//            "TABLE_SCANS": 35670.0,
	//            "DATA_SOURCE_ID": 2.0,
	//            "USECASE_IDENTIFIER": 0.0,
	//            "UNIQUE_USERS": 1.0
	//        },
	//	 	  "valueObjectList": [
	//        		{
	//	              "fieldName": "avgTimerWait",
	//	              "newValue": 19.5723,
	// 	             "oldValue": 19.5723,
	// 	             "deviation": 0.0
	//				},
	//          	{
	//              	"fieldName": "execCount",
	//              	"newValue": 1241.0000,
	//              	"oldValue": 1241.0000,
	//              	"deviation": 0.0
	//          	}
	//      ]
	//    }
	
	private long dataSourceId;
	private String databaseName;
	private String schemaName;
	private String hostUrl;
	private Map<String, Object> data;
	private List<KPI> valueObjectList;
	private String databaseType;

	public DataSourceData(long dataSourceId, String databaseName, String schemaName, String hostUrl,String databaseType,
			Map<String, Object> data, List<KPI> valueObjectList) {
		super();
		this.dataSourceId = dataSourceId;
		this.databaseName = databaseName;
		this.schemaName = schemaName;
		this.hostUrl = hostUrl;
		this.data = data;
		this.databaseType=databaseType;
		this.valueObjectList = valueObjectList;
	}
	public long getDataSourceId() {
		return dataSourceId;
	}
	public void setDataSourceId(long dataSourceId) {
		this.dataSourceId = dataSourceId;
	}
	public String getDatabaseName() {
		return databaseName;
	}
	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}
	public String getSchemaName() {
		return schemaName;
	}
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	public String getHostUrl() {
		return hostUrl;
	}
	public void setHostUrl(String hostUrl) {
		this.hostUrl = hostUrl;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public void setData(Map<String, Object> data) {
		this.data = data;
	}
	public List<KPI> getValueObjectList() {
		return valueObjectList;
	}
	public void setValueObjectList(List<KPI> valueObjectList) {
		this.valueObjectList = valueObjectList;
	}

	public String getDatabaseType() {
		return databaseType;
	}

	public void setDatabaseType(String databaseType) {
		this.databaseType = databaseType;
	}
}


