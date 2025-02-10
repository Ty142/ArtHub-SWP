package Arthub.api;

import Arthub.entity.Account;
import Arthub.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Account") // Định nghĩa route API
public class CheckAccountAPI {

    @Autowired
    private AccountService accountService;

    /**
     * API kiểm tra tài khoản dựa trên Email và Password.
     *
     * @param email    Email của tài khoản
     * @param password Mật khẩu của tài khoản
     * @return Trả về `Account` nếu đúng, HTTP 404 nếu sai
     */
    @GetMapping("/{email}/{password}")
    public ResponseEntity<Account> checkAccount(@PathVariable("email") String email,
                                                @PathVariable("password") String password) {
        System.out.println("🔍 Checking account for Email: " + email);

        // Gọi service để kiểm tra thông tin tài khoản
        Account account = accountService.getAccountByEmailAndPassword(email, password);

        if (account == null || account.getStatus()==0) {
            System.out.println("❌ Invalid Email or Password");
            return ResponseEntity.notFound().build(); // HTTP 404 nếu sai
        }

        System.out.println("✅ Account found: " + account);
        return ResponseEntity.ok(account); // Trả về HTTP 200 nếu đúng
    }
}
