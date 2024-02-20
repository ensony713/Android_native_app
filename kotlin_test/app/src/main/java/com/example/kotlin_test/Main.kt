package com.example.kotlin_test

// 해당 class를 상속 가능한 class로 만드려면 open을 붙여줘야 함
// kotlin에서는 모든 class는 원래 최종 class이기 때문에 확장할 수 없음

// internal: protected와 유사하게 sub class에서 액세스 가능하고 모듈 외부에서 액세스 불가능하지만, 동일한 모듈에서는 액세스 가능함
internal open class SmartDevice protected constructor (val name: String, val category: String) {

    //lateinit val alias: String
    var deviceStatus = "online"
        protected set
    open val deviceType = "unknown"

    constructor(name: String, category: String, statusCode: Int) : this(name, category) {
        deviceStatus = when (statusCode) {
            0 -> "offline"
            1 -> "online"
            else -> "unknown"
        }
    }

    // override 하려면 대상 메소드 앞에 open 키워드 붙여야 함
    open fun turnOn() {
        println("$name is turned on.")
    }
    open fun turnOff() {
        println("$name is turned off.")
    }
}

internal class SmartTvDevice(deviceName: String, deviceCategory: String) : SmartDevice(name = deviceName, category = deviceCategory) {

    override val deviceType = "Smart TV"

    private var speakerVolume = 3
        get() = field
        set(value) {
            if (value in 0..100) { field = value }
        }

    private var channelNumber = 1
        set(value) {
            if (value in 0..100) { field = value }
        }

    fun increaseSpeakerVolume() {
        speakerVolume++
        println("Speaker volume increased to $speakerVolume.")
    }

    fun nextChannel() {
        channelNumber++
        println("Speaker volume increased to $channelNumber.")
    }

    override fun turnOn() {
        super.turnOn()
        deviceStatus = "on"
        println(
            "$name is turned on. Speaker volume is set to $speakerVolume and channel number is " +
                    "set to $channelNumber."
        )
    }

    override fun turnOff() {
        deviceStatus = "off"
        println("$name turned off")
    }
}

internal class SmartLightDevice(deviceName: String, deviceCategory: String) : SmartDevice(name = deviceName, category = deviceCategory) {

    private var brightnessLevel = 0
        set(value) {
            if (value in 0..100) {
                field = value
            }
        }

    fun increaseBrightness() {
        brightnessLevel++
        println("Brightness increased to $brightnessLevel.")
    }

    override fun turnOn() {
        deviceStatus = "on"
        brightnessLevel = 2
        println("$name turned on. The brightness level is $brightnessLevel.")
    }

    override fun turnOff() {
        deviceStatus = "off"
        brightnessLevel = 0
        println("Smart Light turned off")
    }
}

internal class SmartHome(private val smartTvDevice: SmartTvDevice, private val smartLightDevice: SmartLightDevice) {

    // SmartHome -{Has-A}-> SmartTvDevice
    // SmartTvDevice -{Is-A}-> SmartDevice
    private var deviceTurnOnCount = 0

    fun turnOnTv() {
        ++deviceTurnOnCount
        smartTvDevice.turnOn()
    }

    fun turnOffTv() {
        --deviceTurnOnCount
        smartTvDevice.turnOff()
    }

    fun increaseTvVolume() {
        smartTvDevice.increaseSpeakerVolume()
    }

    fun changeTvChannelToNext() {
        smartTvDevice.nextChannel()
    }

    fun turnOnLight() {
        ++deviceTurnOnCount
        smartLightDevice.turnOn()
    }

    fun turnOffLight() {
        --deviceTurnOnCount
        smartLightDevice.turnOff()
    }

    fun increaseLightBrightness() {
        smartLightDevice.increaseBrightness()
    }

    fun turnOffAllDevice() {
        turnOffTv()
        turnOffLight()
    }
}

fun main() {
    val device2 = SmartDevice(name = "Home Camera", category = "Camera", statusCode = 0)
    device2.turnOn()

    val tv = SmartTvDevice("TV", "Entertainment")
    val light = SmartLightDevice("LED", "Light")

    tv.turnOn()
    tv.turnOff()

    val smartDevice: SmartDevice = light as SmartDevice // upcast
    smartDevice.turnOn()

    val moodLight: SmartLightDevice = smartDevice as SmartLightDevice // downcast
    moodLight.turnOn()
}