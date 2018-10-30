package com.bluecedar.analyzer.security.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
/**
 * 
 * @author Ramu Enugala
 *
 */
@Entity
public class Device {
	
	@NotNull
	@NotEmpty
    private String deviceUID;

    
	public String getDeviceUID() {
		return deviceUID;
	}

	public void setDeviceUID(String deviceUID) {
		this.deviceUID = deviceUID;
	}


}
