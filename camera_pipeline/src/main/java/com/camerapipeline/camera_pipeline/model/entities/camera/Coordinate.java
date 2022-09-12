package com.camerapipeline.camera_pipeline.model.entities.camera;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.Range;

@Embeddable
public class Coordinate implements Serializable{
	@Range(min = -90, max=90)
	@Column(name="latitude", columnDefinition="NUMERIC(10,8)")
	private Double latitude;
	
	@Range(min = -180, max=180)
	@Column(name="longitude", columnDefinition="NUMERIC(11,8)")
	private Double longitude;
	
    public Coordinate() {}
    public Coordinate(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Coordinate latitude(Double latitude) {
        setLatitude(latitude);
        return this;
    }

    public Coordinate longitude(Double longitude) {
        setLongitude(longitude);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Coordinate)) {
            return false;
        }
        Coordinate coordinate = (Coordinate) o;
        return Objects.equals(latitude, coordinate.latitude) && Objects.equals(longitude, coordinate.longitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public String toString() {
        return "{" +
            " latitude='" + getLatitude() + "'" +
            ", longitude='" + getLongitude() + "'" +
            "}";
    }	
}

