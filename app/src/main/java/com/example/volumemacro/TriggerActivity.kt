package com.example.volumemacro

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast

/**
 * Activity trong suốt, không hiện giao diện gì.
 * Đây là icon thứ 2 trên màn hình chính (tên "Chạm Macro") — bấm vào là
 * lùi app xuống nền ngay lập tức rồi thực hiện cú chạm tại tọa độ đã lưu
 * bằng lệnh shell (yêu cầu máy đã ROOT), để cú chạm rơi trúng vào app/game
 * đang mở phía dưới thay vì rơi vào chính app này.
 *
 * App không dùng Dịch vụ Trợ năng (Accessibility) nữa, nên đây là cách
 * DUY NHẤT còn lại để giả lập chạm màn hình — và nó chỉ hoạt động nếu
 * máy đã có quyền root.
 */
class TriggerActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefs = getSharedPreferences(MainActivity.PREFS_NAME, MODE_PRIVATE)
        val x = prefs.getInt(MainActivity.KEY_X, 500)
        val y = prefs.getInt(MainActivity.KEY_Y, 800)

        // Lùi task này xuống nền ngay để lộ ra app/game phía dưới trước khi chạm.
        moveTaskToBack(true)

        Thread {
            val success = tapViaRoot(x, y)
            if (!success) {
                Handler(Looper.getMainLooper()).post {
                    Toast.makeText(
                        applicationContext,
                        "Không chạm được: cần quyền ROOT nhưng không lấy được quyền root trên máy này.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            Handler(Looper.getMainLooper()).post { finish() }
        }.start()
    }

    private fun tapViaRoot(x: Int, y: Int): Boolean {
        return try {
            val process = Runtime.getRuntime().exec(arrayOf("su", "-c", "input tap $x $y"))
            val exitCode = process.waitFor()
            exitCode == 0
        } catch (e: Exception) {
            false
        }
    }
}
