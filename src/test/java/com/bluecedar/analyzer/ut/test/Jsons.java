package com.bluecedar.analyzer.ut.test;

/**
 * 
 * @author Ramu Enugala test branch 
 *
 */

public class Jsons {
	
	public static String logJson = "{\r\n" + 
			"\r\n" + 
			"    \"msgtype\": \"logs\",\r\n" + 
			"\r\n" + 
			"    \"devtype\": \"appliance\",\r\n" + 
			"\r\n" + 
			"    \"devname\": \"bluecedar_log\",\r\n" + 
			"\r\n" + 
			"    \"id\": \"172.10.34.2\",\r\n" + 
			"\r\n" + 
			"    \"logs\": [\r\n" + 
			"\r\n" + 
			"        {\"time\": \"2018-10-05T13:34:08.777304983-04:00\",\r\n" + 
			"\r\n" + 
			"        \"level\": \"INFO\",\r\n" + 
			"\r\n" + 
			"        \"class\": \"IPSEC\",\r\n" + 
			"\r\n" + 
			"        \"message\": \"This is an informational log message for the appliance ipsec class\"\r\n" + 
			"\r\n" + 
			"        },\r\n" + 
			"\r\n" + 
			"        {\"time\": \"2018-10-05T13:36:08.827314943-04:00\",\r\n" + 
			"\r\n" + 
			"        \"level\": \"INFO\",\r\n" + 
			"\r\n" + 
			"        \"class\": \"IPSEC\",\r\n" + 
			"\r\n" + 
			"        \"message\": \"This is another informational log message for the appliance ipsec class\"\r\n" + 
			"\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"    ]\r\n" + 
			"\r\n" + 
			"  }";

	public static String reportJson = "{\r\n" + 
		"\r\n" + 
		"    \"msgtype\": \"reports\",\r\n" + 
		"\r\n" + 
		"    \"devtype\": \"appliance\",\r\n" + 
		"\r\n" + 
		"    \"devname\": \"bluecedar_report\",\r\n" + 
		"\r\n" + 
		"    \"id\": \"172.10.34.2\",\r\n" + 
		"\r\n" + 
		"    \"reports\": [\r\n" + 
		"\r\n" + 
		"       {\"type\": \"policyreject\",\r\n" + 
		"\r\n" + 
		"       \"reason\":\"Attempting login with jailbroken device\",\r\n" + 
		"\r\n" + 
		"       \"sessionId\":\r\n" + 
		"\r\n" + 
		"           {\"username\":\"jruser\",\r\n" + 
		"\r\n" + 
		"           \"device\":\r\n" + 
		"\r\n" + 
		"               {\"osType\":\"android\",\r\n" + 
		"\r\n" + 
		"               \"hardware\":\"x86_64\",\r\n" + 
		"\r\n" + 
		"               \"version\":\"12\",\r\n" + 
		"\r\n" + 
		"               \"carrierName\":\"zeterminal\",\r\n" + 
		"\r\n" + 
		"               \"mobileCountryCode\":\"508\",\r\n" + 
		"\r\n" + 
		"               \"mobileNetworkCode\":\"805\",\r\n" + 
		"\r\n" + 
		"               \"uniqueId\":\"00:00:00:00:00:03\",\r\n" + 
		"\r\n" + 
		"               \"jailbroken\":true\r\n" + 
		"\r\n" + 
		"               },\r\n" + 
		"\r\n" + 
		"           \"app\":\r\n" + 
		"\r\n" + 
		"               {\"assignedIp\":\"\",\r\n" + 
		"\r\n" + 
		"               \"federation\":\"com.bcn.map.federation.FEDDEFAULT\",\r\n" + 
		"\r\n" + 
		"               \"appName\":\"PromCLI\",\r\n" + 
		"\r\n" + 
		"               \"appPackageId\":\"com.bcn.promcli\",\r\n" + 
		"\r\n" + 
		"               \"appVersion\":\"1.0\",\r\n" + 
		"\r\n" + 
		"               \"appUuid\":\"58C4EFBE-DA93-4FD7-9E74-F0A568DDAAAA\",\r\n" + 
		"\r\n" + 
		"               \"appGsid\":1706955781\r\n" + 
		"\r\n" + 
		"               }\r\n" + 
		"\r\n" + 
		"             }\r\n" + 
		"\r\n" + 
		"       },\r\n" + 
		"\r\n" + 
		"       {\"type\": \"sessionstart\",\r\n" + 
		"\r\n" + 
		"        \"sessionId\":\r\n" + 
		"\r\n" + 
		"            {\"username\":\"jruser\",\r\n" + 
		"\r\n" + 
		"            \"device\":\r\n" + 
		"\r\n" + 
		"                {\"osType\":\"ios\",\r\n" + 
		"\r\n" + 
		"                \"deviceName\":\"Jims Phone\",\r\n" + 
		"\r\n" + 
		"                \"hardware\":\"iPhone10,2\",\r\n" + 
		"\r\n" + 
		"                \"version\":\"12.0\",\r\n" + 
		"\r\n" + 
		"                \"carrierName\":\"Verizon\",\r\n" + 
		"\r\n" + 
		"                \"mobileCountryCode\":\"311\",\r\n" + 
		"\r\n" + 
		"                \"mobileNetworkCode\":\"480\",\r\n" + 
		"\r\n" + 
		"                \"uniqueId\":\"c2a7d16fc61cde9518ef31c232c46fac7657ee635ee2c526123460761edb885a\",\r\n" + 
		"\r\n" + 
		"                \"jailbroken\":false\r\n" + 
		"\r\n" + 
		"                },\r\n" + 
		"\r\n" + 
		"            \"app\":\r\n" + 
		"\r\n" + 
		"                {\"assignedIp\":\"10.42.5.14\",\r\n" + 
		"\r\n" + 
		"                \"federation\":\"group.8PZ8T7ZJD7.com.bcn.shareddatastore.default\",\r\n" + 
		"\r\n" + 
		"                \"appName\":\"TouchDown\",\r\n" + 
		"\r\n" + 
		"                \"appPackageId\":\"com.bluecedar.wrapped.com.nitrodesk.tdi.TouchDown\",\r\n" + 
		"\r\n" + 
		"                \"appVersion\":\"9\",\r\n" + 
		"\r\n" + 
		"                \"atlasClientVersion\":\"3.22.0\",\r\n" + 
		"\r\n" + 
		"                \"appUuid\":\"7BFD2CF5-48A6-4D8D-BA8C-E7764C3AAC83\",\r\n" + 
		"\r\n" + 
		"                \"appGsid\":1706955780,\r\n" + 
		"\r\n" + 
		"                \"acceptedEula\":true,\r\n" + 
		"\r\n" + 
		"                \"appTampered\":false\r\n" + 
		"\r\n" + 
		"                }\r\n" + 
		"\r\n" + 
		"            }\r\n" + 
		"\r\n" + 
		"       }\r\n" + 
		"\r\n" + 
		"    ]\r\n" + 
		"\r\n" + 
		"  }";


	public static String invalidJson = "{\r\n" + 
			"\r\n" + 
			"  \"sessionId\": {\r\n" + 
			"\r\n" + 
			"    \"username\": \"Ramu1\",\r\n" + 
			"\r\n" + 
			"    \"device\": {\r\n" + 
			"\r\n" + 
			"      \"osType\": \"android\",\r\n" + 
			"\r\n" + 
			"      \"hardware\": \"samsung SM-T530 - matissewifixx - unknown\",\r\n" + 
			"\r\n" + 
			"      \"version\": \"4.4.2\",\r\n" + 
			"\r\n" + 
			"      \"carrierName\": \"\",\r\n" + 
			"\r\n" + 
			"      \"mobileCountryCode\": \"\",\r\n" + 
			"\r\n" + 
			"     \"mobileNetworkCode\": \"\",\r\n" + 
			"\r\n" + 
			"      \"uniqueId\": \"d49a539a0015f921158c46ef6ff91dd0be1d64cb5bc8c545f6a25b24107aae27\",\r\n" + 
			"\r\n" + 
			"      \"jailbroken\": false\r\n" + 
			"\r\n" + 
			"    },\r\n" + 
			"\r\n" + 
			"    \"app\": {\r\n" + 
			"\r\n" + 
			"      \"assignedIp\": \"10.42.5.15\",\r\n" + 
			"\r\n" + 
			"      \"federation\": \"group.AQOGLAHBDI.com.bcn.shareddatastore.default\",\r\n" + 
			"\r\n" + 
			"      \"appName\": \"TouchDown HD\",\r\n" + 
			"\r\n" + 
			"      \"appPackageId\": \"com.nitrodesk.honey.nitroid\",\r\n" + 
			"\r\n" + 
			"      \"appVersion\": \"9.0.00450\",\r\n" + 
			"\r\n" + 
			"      \"atlasClientVersion\": \"3.21.1\",\r\n" + 
			"\r\n" + 
			"      \"appUuid\": \"\",\r\n" + 
			"\r\n" + 
			"      \"appGsid\": 2712269178,\r\n" + 
			"\r\n" + 
			"      \"acceptedEula\": true,\r\n" + 
			"\r\n" + 
			"      \"appTampered\": false\r\n" + 
			"\r\n" + 
			"    }\r\n" + 
			"\r\n" + 
			"  }\r\n" + 
			"\r\n" + 
			"}";

	
	public static String parsingExceptionJson = "{\r\n" + 
			"\r\n" + 
			"    \"msgtype\": \"logs\",\r\n" + 
			"\r\n" + 
			"    \"devtype\": \"appliance\",\r\n" + 
			"\r\n" + 
			"    \"devname\": \"name1,\r\n" + 
			"\r\n" + 
			"    \"id\": \"172.10.34.2\",\r\n" + 
			"\r\n" + 
			"    \"logs\": [\r\n" + 
			"\r\n" + 
			"        {\"time\": \"2018-10-05T13:34:08.777304983-04:00\",\r\n" + 
			"\r\n" + 
			"        \"level\": \"INFO\",\r\n" + 
			"\r\n" + 
			"        \"class\": \"IPSEC\",\r\n" + 
			"\r\n" + 
			"        \"message\": \"This is an informational log message for the appliance ipsec class\"\r\n" + 
			"\r\n" + 
			"        },\r\n" + 
			"\r\n" + 
			"        {\"time\": \"2018-10-05T13:36:08.827314943-04:00\",\r\n" + 
			"\r\n" + 
			"        \"level\": \"INFO\",\r\n" + 
			"\r\n" + 
			"        \"class\": \"IPSEC\",\r\n" + 
			"\r\n" + 
			"        \"message\": \"This is another informational log message for the appliance ipsec class\"\r\n" + 
			"\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"    ]\r\n" + 
			"\r\n" + 
			"  }";
	
	
	public static String wrongJsonSchema = "{\r\n" + 
			"\r\n" + 
			"    \"msgtype\": \"logs\",\r\n" + 
			"\r\n" + 
			"    \"devtype\": \"appliance\",\r\n" + 
			"\r\n" + 
			"    \"devname\": \"log 1\",\r\n" + 
			"\r\n" + 
			"    \"logs\": [\r\n" + 
			"\r\n" + 
			"        {\"time\": \"2018-10-05T13:34:08.777304983-04:00\",\r\n" + 
			"\r\n" + 
			"        \"level\": \"INFO\",\r\n" + 
			"\r\n" + 
			"        \"class\": \"IPSEC\",\r\n" + 
			"\r\n" + 
			"        \"message\": \"This is an informational log message for the appliance ipsec class\"\r\n" + 
			"\r\n" + 
			"        },\r\n" + 
			"\r\n" + 
			"        {\"time\": \"2018-10-05T13:36:08.827314943-04:00\",\r\n" + 
			"\r\n" + 
			"        \"level\": \"INFO\",\r\n" + 
			"\r\n" + 
			"        \"class\": \"IPSEC\",\r\n" + 
			"\r\n" + 
			"        \"message\": \"This is another informational log message for the appliance ipsec class\"\r\n" + 
			"\r\n" + 
			"        }\r\n" + 
			"\r\n" + 
			"    ]\r\n" + 
			"\r\n" + 
			"  }";
}
