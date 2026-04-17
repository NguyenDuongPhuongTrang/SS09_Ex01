package bt.bai1.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CartController {
    // Lỗi do redirect khi chuyển trang sẽ làm mất dữ liệu của request nên khi chuyển trang thấy cart có size = 0
    // Giải pháp: lưu trữ trong session

    @GetMapping("/add-to-cart")
    public String showForm() {
        return "checkout";
    }

    @PostMapping("/add-to-cart")
    public String addToCard(
            @RequestParam("productId") String productId,
            HttpSession session
    ) {
        List<String> cart = (List<String>) session.getAttribute("cart");
        if (cart == null) {
            cart = new ArrayList<>();
        }
        cart.add(productId);
        session.setAttribute("cart", cart);
        return "redirect:/checkout";
    }

    @GetMapping("/checkout")
    public String viewCheckout(
            HttpSession session,
            Model model
    ) {
        List<String> cart = (List<String>) session.getAttribute("cart");
        model.addAttribute("cart", cart);
        return "checkout-page";
    }
}
