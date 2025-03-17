package Arthub.api;

import Arthub.entity.Withdraw;
import Arthub.service.WithdrawService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/withdraw")
public class WithdrawAPI {
    @Autowired
    WithdrawService withdrawService;
    @PostMapping("/SaveWithdraw/")
    public String saveWithdraw(@RequestBody Withdraw withdraw) {
        withdrawService.addWithdraw(withdraw);
        return "Withdrawal saved successfully";
    }
}
