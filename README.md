# Volume Macro

App Android: chọn 1 điểm trên màn hình, rồi bấm vào icon **"Chạm Macro"** để tự động chạm vào điểm đó
(ví dụ chạm vào game/app khác đang mở phía dưới).

## Quan trọng: App này cần máy đã ROOT

Bản này **không dùng Dịch vụ Trợ năng (Accessibility)** nữa. Vì Android chỉ cho phép giả lập chạm màn hình
qua 2 cách — Accessibility Service hoặc quyền Root — nên khi bỏ Accessibility đi, **quyền Root trở thành
cách DUY NHẤT còn lại** để tính năng chạm hoạt động.

- Nếu máy đã root: mọi thứ hoạt động bình thường, không cần bật gì thêm trong Cài đặt.
- Nếu máy **chưa root**: icon "Chạm Macro" sẽ hiện thông báo lỗi mỗi lần bấm, vì không có cách hợp lệ nào
  khác để giả lập chạm màn hình trên Android khi không có Accessibility lẫn Root.

## Cách dùng

1. Mở app **Volume Macro** (icon chính).
2. Bấm **"Chọn điểm trên màn hình"** → cấp quyền overlay nếu được hỏi → kéo chấm xanh tới đúng vị trí cần
   chạm → bấm **"Xác nhận điểm"**. (Hoặc tự gõ tay tọa độ X, Y rồi bấm **Lưu**.)
3. Mở app/game mà bạn muốn chạm vào (ví dụ một game).
4. Mở icon thứ 2 tên **"Chạm Macro"** (nằm cùng màn hình chính, cạnh icon "Volume Macro"). App sẽ tự lùi
   xuống nền và chạm vào tọa độ đã lưu, rơi đúng vào app/game bạn đang mở.

## Cách lấy file APK

### Cách 1 — Android Studio
Mở thư mục project bằng Android Studio, bấm Run hoặc Build > Build APK(s).

### Cách 2 — GitHub Actions (tự build trên mây, không cần cài gì)
Project có sẵn `.github/workflows/build.yml`. Push code lên GitHub, vào tab Actions chờ build xong, tải
APK ở mục Artifacts.

## Tuỳ chỉnh / mở rộng

- Muốn nhiều điểm chạm liên tiếp: sửa `TriggerActivity.kt`, gọi nhiều lệnh `input tap x y` (hoặc thêm
  `input swipe`) nối tiếp nhau trong hàm `tapViaRoot`.
- Muốn quay lại cách dùng Accessibility (không cần root nhưng cần bật Trợ năng trong Cài đặt) — đây là
  phiên bản trước đó của app; nếu máy bạn khắc phục được lỗi không mở được màn hình Trợ năng, cách đó
  tiện hơn nhiều vì không đòi hỏi root.

## Lưu ý

- Giả lập chạm màn hình có thể vi phạm điều khoản dịch vụ của một số ứng dụng (đặc biệt game có
  anti-cheat). Bạn tự chịu trách nhiệm khi sử dụng.
- Toạ độ X, Y là toạ độ pixel tuyệt đối của màn hình, không phải theo app cụ thể.
- Việc root máy có rủi ro (mất bảo hành, có thể làm hỏng máy nếu thao tác sai, hoặc dính mã độc nếu
  dùng công cụ root không rõ nguồn gốc) — cân nhắc kỹ trước khi root chỉ để dùng tính năng này.
