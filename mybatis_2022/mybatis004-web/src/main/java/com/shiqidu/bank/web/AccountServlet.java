package com.shiqidu.bank.web;

import com.shiqidu.bank.exception.AccountNotEnoughException;
import com.shiqidu.bank.exception.TransferException;
import com.shiqidu.bank.service.AccountService;
import com.shiqidu.bank.service.impl.AccountServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/transfer")
public class AccountServlet extends HttpServlet {

    private final AccountService accountService = new AccountServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fromActNo = request.getParameter("fromActNo");
        String toActNo = request.getParameter("toActNo");
        Double money = Double.parseDouble(request.getParameter("money"));

        try {
            accountService.transfer(fromActNo, toActNo, money);
            response.sendRedirect(request.getContextPath() + "/success.html");
        } catch (AccountNotEnoughException e) {
            response.sendRedirect(request.getContextPath() + "/error1.html");
        } catch (TransferException e) {
            response.sendRedirect(request.getContextPath() + "/error2.html");
        }

    }
}
