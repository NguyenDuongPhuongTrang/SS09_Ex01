### Phân tích logic

Điều kiện `if (guestName == null)` thực chất **không có tác dụng trong lần truy cập đầu tiên**, vì nó **không bao giờ được chạy tới**.

#### Lý do

- `@CookieValue("guest_name")` mặc định có `required = true`
- Khi người dùng truy cập lần đầu → chưa có cookie `guest_name`
- Trong quá trình binding, Spring sẽ **ném exception (`MissingCookieValueException`)**
- Request bị dừng với HTTP 400 → method `homePage` **không được gọi**

#### Hệ quả

- Biến `guestName` không bao giờ nhận giá trị `null`
- Điều kiện `if (guestName == null)` **không thể xảy ra**

→ Trở thành **dead code**

#### Kết luận

Để xử lý trường hợp người dùng truy cập lần đầu, cần cấu hình để cookie **có thể không tồn tại** (ví dụ: `required = false`)