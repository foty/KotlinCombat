package com.example.upacket

/**
 * 数据解析扩展函数
 */
fun <T> BaseResp<T>.dataConvert(): T {
    if (code == 200) {
        return data
    } else {
        throw Exception(msg)
    }
}
