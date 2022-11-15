package org.meshtastic.common.model

import com.geeksville.mesh.MeshProtos
import com.geeksville.mesh.util.anonymize

data class Position(
    val latitude: Double,
    val longitude: Double,
    val altitude: Int,
    val time: Int = currentTime() // default to current time in secs (NOT MILLISECONDS!)
) : Parcelable {
    companion object {
        /// Convert to a double representation of degrees
        fun degD(i: Int) = i * 1e-7
        fun degI(d: Double) = (d * 1e7).toInt()

        fun currentTime() = (System.currentTimeMillis() / 1000).toInt()
    }

    /** Create our model object from a protobuf.  If time is unspecified in the protobuf, the provided default time will be used.
     */
    constructor(p: MeshProtos.Position, defaultTime: Int = currentTime()) : this(
        // We prefer the int version of lat/lon but if not available use the depreciated legacy version
        degD(p.latitudeI),
        degD(p.longitudeI),
        p.altitude,
        if (p.time != 0) p.time else defaultTime
    )

    /// @return distance in meters to some other node (or null if unknown)
    fun distance(o: Position) = latLongToMeter(latitude, longitude, o.latitude, o.longitude)

    /// @return bearing to the other position in degrees
    fun bearing(o: Position) = bearing(latitude, longitude, o.latitude, o.longitude)

    // If GPS gives a crap position don't crash our app
    fun isValid(): Boolean {
        return latitude != 0.0 && longitude != 0.0 &&
                (latitude >= -90 && latitude <= 90.0) &&
                (longitude >= -180 && longitude <= 180)
    }

    override fun toString(): String {
        return "Position(lat=${latitude.anonymize}, lon=${longitude.anonymize}, alt=${altitude.anonymize}, time=${time})"
    }
}

