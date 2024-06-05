package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.authentication.UserDetailsServiceImpl;
import com.example.demo.constant.UrlConst;
import com.example.demo.dto.UserAddRequest;
import com.example.demo.service.SignupService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class SignUpController {
	
    private final SignupService signupService;
	
	private final AuthenticationManager authenticationManager;
	
	private final UserDetailsServiceImpl userDetailsServiceImpl;
	
    private final PasswordEncoder passwordEncoder; // パスワードエンコーダを追加
	
    
//    自動ログインで追記
//	 public void autoLogin(String email, String password) {
//	        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(email);
//	        UsernamePasswordAuthenticationToken authToken =
//	                new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//
//	        try {
//	            Authentication authentication = authenticationManager.authenticate(authToken);
//	            SecurityContextHolder.getContext().setAuthentication(authentication);
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
//	    }
    
    /**
     * ユーザー新規登録画面を表示
     * @param model Model
     * @return ユーザー情報一覧画面
     */
	@GetMapping(UrlConst.SIGNUP)
		public String displayAdd(Model model) {
	        model.addAttribute("userAddRequest", new UserAddRequest());
		return "signup";
	}
	
	/**
     * ユーザー新規登録
     * @param userRequest リクエストデータ
     * @param model Model
     * @return ユーザー情報一覧画面
     */
    @PostMapping(UrlConst.SIGNUP)
    public String create(@Validated @ModelAttribute UserAddRequest userRequest, BindingResult result, 
    		Model model,HttpServletRequest request)
   {
        if (result.hasErrors()) {
            // 入力チェックエラーの場合
            List<String> errorList = new ArrayList<String>();
            for (ObjectError error : result.getAllErrors()) {
                errorList.add(error.getDefaultMessage());
            }
            model.addAttribute("validationError", errorList);
            return "signup";
        }
        

        
        
//        自動ログインで追記
        
//        // 平文のパスワードを保持
//        String rawPassword = userRequest.getPassword();
//
//        // パスワードのエンコード
//        userRequest.setPassword(passwordEncoder.encode(userRequest.getPassword()));
//        
//        try {
//            signupService.save(userRequest);
//        } catch (IllegalArgumentException e) {
//            model.addAttribute("validationError", Collections.singletonList(e.getMessage()));
//            return "signup";
//        }
//        
//        // ユーザー情報が登録できた場合※元の記述
      signupService.save(userRequest);
      System.out.println("User saved: " + userRequest);
      
    try {
    // ユーザー情報をロード
    UserDetails 
    userDetails = userDetailsServiceImpl.loadUserByUsername(userRequest.getEmail());

    // ロードしたユーザー情報をログに出力
    System.out.println("User details: " + userDetails);

    // 認証トークン（ユーザー名、パスワード、およびユーザーの権限情報を保持）の作成
    UsernamePasswordAuthenticationToken authToken = 
        new UsernamePasswordAuthenticationToken(userDetails, userRequest.getPassword(), userDetails.getAuthorities());

    // 作成した認証トークンをログに出力
    System.out.println("Authentication token: " + authToken);

    // セキュリティコンテキストに認証情報を設定
    SecurityContextHolder.getContext().setAuthentication(authToken);

    // 認証情報をセッションに保存
    HttpSession session = request.getSession(true);
    session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

} catch (Exception e) {
    // エラーメッセージをログに出力
    System.out.println("Login error: " + e.getMessage());
    model.addAttribute("loginError", "ログインに失敗しました: " + e.getMessage());
    return "/signup";
}
        
        return "redirect:/menu"; // トップ画面へ遷移するように変更
    }
   
}
