package com.sport.jth.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sport.jth.model.Board;
import com.sport.jth.model.FacebookProfile;
import com.sport.jth.model.GoogleProfile;
import com.sport.jth.model.KakaoProfile;
import com.sport.jth.model.NaverProfile;
import com.sport.jth.model.OAuthToken;
import com.sport.jth.model.User;
import com.sport.jth.repository.NewsCrawling;
import com.sport.jth.service.BoardService;
import com.sport.jth.service.UserService;

@Controller
public class IndexController {
	
	@Value("${cos.key}")
	private String cosKey;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private NewsCrawling newsCrawling;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	final String football = "Football";
	final String baseball = "Baseball";
	final String soccer = "Soccer";
	final String basketball = "Basketball";
	final String common = "Common";
	
	//???????????????
	@GetMapping({"/", ""})
	public String index(Model model) {
		
		//football-news
		Elements footballEle = newsCrawling.getBaseballNews("general");
		List<String> footballImg = new ArrayList<>();
		List<String> footballTitle = new ArrayList<>();
		List<String> footballHref = new ArrayList<>();
		int size = 5;
		if(footballEle.size() > 0) {
			for(int i=0; i<size; i++) {
				String img = footballEle.get(i).getElementsByAttributeValue("class", "lazyLoadImage").attr("lazy-src");
				String title = footballEle.get(i).getElementsByAttributeValue("class", "title").text();
				String href = footballEle.get(i).attr("href");
				footballImg.add(img);
				footballTitle.add(title);
				footballHref.add(href);
			}
		}
		
		//baseball-news
		Elements baseballEle = newsCrawling.getBaseballNews("kbaseball");
		List<String> baseballImg = new ArrayList<>();
		List<String> baseballTitle = new ArrayList<>();
		List<String> baseballHref = new ArrayList<>();
		if(baseballEle.size() > 0) {
			for(int i=0; i<size; i++) {
				String img = baseballEle.get(i).getElementsByAttributeValue("class", "lazyLoadImage").attr("lazy-src");
				String title = baseballEle.get(i).getElementsByAttributeValue("class", "title").text();
				String href = baseballEle.get(i).attr("href");
				baseballImg.add(img);
				baseballTitle.add(title);
				baseballHref.add(href);
			}
		}
		
		//soccer-news
		Elements soccerEle = newsCrawling.getBaseballNews("kfootball");
		List<String> soccerImg = new ArrayList<>();
		List<String> soccerTitle = new ArrayList<>();
		List<String> soccerHref = new ArrayList<>();
		if(soccerEle.size() > 0) {
			for(int i=0; i<size; i++) {
				String img = soccerEle.get(i).getElementsByAttributeValue("class", "lazyLoadImage").attr("lazy-src");
				String title = soccerEle.get(i).getElementsByAttributeValue("class", "title").text();
				String href = soccerEle.get(i).attr("href");
				soccerImg.add(img);
				soccerTitle.add(title);
				soccerHref.add(href);
			}
		}
		
		//basketball-news
		Elements basketballEle = newsCrawling.getBaseballNews("basketball");
		List<String> basketballImg = new ArrayList<>();
		List<String> basketballTitle = new ArrayList<>();
		List<String> basketballHref = new ArrayList<>();
		if(basketballEle.size() > 0) {
			for(int i=0; i<size; i++) {
				String img = basketballEle.get(i).getElementsByAttributeValue("class", "lazyLoadImage").attr("lazy-src");
				String title = basketballEle.get(i).getElementsByAttributeValue("class", "title").text();
				String href = basketballEle.get(i).attr("href");
				basketballImg.add(img);
				basketballTitle.add(title);
				basketballHref.add(href);
			}
		}
		
		
		model.addAttribute("footBoards", boardService.findByBoardsWithCountDescAndGroupByCategory(football));
		model.addAttribute("baseBoards", boardService.findByBoardsWithCountDescAndGroupByCategory(baseball));
		model.addAttribute("socBoards", boardService.findByBoardsWithCountDescAndGroupByCategory(soccer));
		model.addAttribute("basBoards", boardService.findByBoardsWithCountDescAndGroupByCategory(basketball));
		model.addAttribute("comBoards", boardService.findByBoardsWithCountDescAndGroupByCategory(common));
		model.addAttribute("baseballImg", baseballImg);
		model.addAttribute("baseballTitle", baseballTitle);
		model.addAttribute("baseballHref", baseballHref);
		model.addAttribute("soccerImg", soccerImg);
		model.addAttribute("soccerTitle", soccerTitle);
		model.addAttribute("soccerHref", soccerHref);
		model.addAttribute("basketballImg", basketballImg);
		model.addAttribute("basketballTitle", basketballTitle);
		model.addAttribute("basketballHref", basketballHref);
		model.addAttribute("footballImg", footballImg);
		model.addAttribute("footballTitle", footballTitle);
		model.addAttribute("footballHref", footballHref);
		return "index";
	}
	
	//???????????? ?????????
	@GetMapping("/auth/joinForm")
	public String joinForm() {
		return "user/joinForm";
	}
	
	//????????? ?????????
	@GetMapping("/auth/loginForm")
	public String loginForm() {
		return "user/loginForm";
	}
	
	//????????? ?????????
	@GetMapping("/auth/list/{category}")
	public String list(@PathVariable String category, Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Board> boards = boardService.sortListByCategory(pageable, category);
		
		int currPage = boards.getNumber();
		int totalPages = boards.getTotalPages();
		int pageBlock = 10;
		int startBlockPage = ((currPage)/pageBlock)*pageBlock+1;
		int endBlockPage = startBlockPage+pageBlock-1;
		endBlockPage = totalPages<endBlockPage ? totalPages:endBlockPage;
		model.addAttribute("startBlockPage", startBlockPage);
		model.addAttribute("endBlockPage", endBlockPage);
		model.addAttribute("boards", boards);
		model.addAttribute("category", category);
		model.addAttribute("footBoards", boardService.findByBoardsWithCountDescAndGroupByCategory(football));
		model.addAttribute("baseBoards", boardService.findByBoardsWithCountDescAndGroupByCategory(baseball));
		model.addAttribute("socBoards", boardService.findByBoardsWithCountDescAndGroupByCategory(soccer));
		model.addAttribute("basBoards", boardService.findByBoardsWithCountDescAndGroupByCategory(basketball));
		model.addAttribute("comBoards", boardService.findByBoardsWithCountDescAndGroupByCategory(common));
		return "board/list/list";
	}
	
	//?????? ????????? ?????????
	@GetMapping("/auth/list")
	public String searchList(@RequestParam(required = false, defaultValue = "") String search, Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Board> boards = boardService.searchAll(pageable, search);
		
		int currPage = boards.getNumber();
		int totalPages = boards.getTotalPages();
		int pageBlock = 10;
		int startBlockPage = ((currPage)/pageBlock)*pageBlock+1;
		int endBlockPage = startBlockPage+pageBlock-1;
		endBlockPage = totalPages<endBlockPage ? totalPages:endBlockPage;
		model.addAttribute("startBlockPage", startBlockPage);
		model.addAttribute("endBlockPage", endBlockPage);
		model.addAttribute("boards", boards);
		model.addAttribute("search", search);
		return "board/list/searchList";
	}
	
	//????????? ?????? ?????????
	@GetMapping("/auth/list/detail/{category}/{id}")
	public String detail(@PathVariable int id, @PathVariable String category, Model model) {
		boardService.increaseCount(id);
		Board nextBoard = boardService.nextBoard(id, category);
		Board prevBoard = boardService.prevBoard(id, category);
		model.addAttribute("replyNum", boardService.getOne(id).getReplys().size());
		model.addAttribute("board", boardService.getOne(id));
		model.addAttribute("nextBoard", nextBoard);
		model.addAttribute("prevBoard", prevBoard);
		return "board/detail";
	}
	
	//???????????? ?????????
	@GetMapping("/detailForm/{id}")
	public String detailForm(@PathVariable int id) {
		return "user/detailForm";
	}
	
	//???????????? ?????? ?????????
	@GetMapping("/user/checkForm")
	public String checkForm() {
		return "user/checkForm";
	}
	
	//???????????? ?????? ?????????2
		@GetMapping("/user/checkForm2")
		public String checkForm2() {
			return "user/checkForm2";
		}
	
	//???????????? ?????? ?????????
	@GetMapping("/user/updateForm")
	public String updateForm() {
		return "user/updateForm";
	}
	
	//???????????? ?????????
	@GetMapping("/user/deleteForm")
	public String deleteForm() {
		return "user/deleteForm";
	}
	
	//????????? ?????????
	@GetMapping("/board/writeForm")
	public String writeForm() {
		return "board/writeForm";
	}
	
	//????????? ?????????
	@GetMapping("/board/updateForm/{id}")
	public String updateForm(@PathVariable int id, Model model) {
		model.addAttribute("board", boardService.getOne(id));
		return "board/updateForm";
	}
	
	//??? ????????? ?????????
	@GetMapping("/user/mylistForm/{id}")
	public String mylistForm(@PathVariable int id, Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<Board> boards = boardService.getMyList(pageable, id);
		int currPage = boards.getNumber();
		int totalPages = boards.getTotalPages();
		int pageBlock = 10;
		int startBlockPage = ((currPage)/pageBlock)*pageBlock+1;
		int endBlockPage = startBlockPage+pageBlock-1;
		endBlockPage = totalPages<endBlockPage ? totalPages:endBlockPage;
		model.addAttribute("startBlockPage", startBlockPage);
		model.addAttribute("endBlockPage", endBlockPage);
		model.addAttribute("boards", boards);
		model.addAttribute("totalNum", boards.getTotalElements());
		return "user/mylistForm";
	}
	
	//????????? ????????? ??????
	@GetMapping("/auth/kakao/callback")
	public String kakaoCallback(String code) {
		
		//POST ???????????? key=value ???????????? ??????
		RestTemplate rt = new RestTemplate();
		
		//header????????????
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//body????????????
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "client_id");
		params.add("redirect_uri", "http://localhost:8000/auth/kakao/callback");
		params.add("code", code);
		
		//????????? ????????? ????????? ??????????????? ??????
		HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = 
				new HttpEntity<>(params, headers);
		
		//POST ???????????? ???????????? String???????????? ??????
		ResponseEntity<String> response = rt.exchange(
				"https://kauth.kakao.com/oauth/token",
				HttpMethod.POST,
				kakaoTokenRequest,
				String.class
		);
		
		ObjectMapper obMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			 oauthToken = obMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		//POST ???????????? key=value ???????????? ??????
		RestTemplate rt2 = new RestTemplate();
		
		//header????????????
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//????????? ????????? ????????? ??????????????? ??????
		HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest = 
				new HttpEntity<>(headers2);
		
		//POST ???????????? ???????????? String???????????? ??????
		ResponseEntity<String> response2 = rt2.exchange(
				"https://kapi.kakao.com/v2/user/me",
				HttpMethod.POST,
				kakaoProfileRequest,
				String.class
		);
		
		ObjectMapper obMapper2 = new ObjectMapper();
		KakaoProfile kakaoProfile = null;
		
		try {
			kakaoProfile = obMapper2.readValue(response2.getBody(), KakaoProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		User kakaoUser = new User();
		kakaoUser.setUsername(kakaoProfile.getKakao_account().getEmail()+"_kakao");
		kakaoUser.setPassword(cosKey);
		kakaoUser.setOauth("kakao");
		
		//????????? ?????? ???????????? ??????
		User originUser = userService.joinCheck(kakaoUser.getUsername());
		
		//??????????????? ??????
		if(originUser == null) {
			userService.join(kakaoUser);
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} else {
			//???????????? ????????? ??????
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(kakaoUser.getUsername(), cosKey));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
			
		return "redirect:/";
	}

	//????????? ????????? ??????
	@GetMapping("/auth/naver/callback")
	public String naverCallback(String code) {
		
		RestTemplate rt = new RestTemplate();
		
		//header????????????
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//body????????????
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("grant_type", "authorization_code");
		params.add("client_id", "client_id");
		params.add("client_secret", "client_secret");
		params.add("code", code);
		params.add("state", "STATE_STRING");
		
		HttpEntity<MultiValueMap<String, String>> naverTokenRequest = 
				new HttpEntity<>(params, headers);
		
		//POST ???????????? ???????????? String???????????? ??????
		ResponseEntity<String> response = rt.exchange(
				"https://nid.naver.com/oauth2.0/token",
				HttpMethod.POST,
				naverTokenRequest,
				String.class
		);
		
		ObjectMapper obMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			 oauthToken = obMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		//POST ???????????? key=value ???????????? ??????
		RestTemplate rt2 = new RestTemplate();
		
		//header????????????
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//????????? ????????? ????????? ??????????????? ??????
		HttpEntity<MultiValueMap<String, String>> naverProfileRequest = 
				new HttpEntity<>(headers2);
		
		//POST ???????????? ???????????? String???????????? ??????
		ResponseEntity<String> response2 = rt2.exchange(
				"https://openapi.naver.com/v1/nid/me",
				HttpMethod.POST,
				naverProfileRequest,
				String.class
		);
		
		ObjectMapper obMapper2 = new ObjectMapper();
		NaverProfile naverProfile = null;
		
		try {
			naverProfile = obMapper2.readValue(response2.getBody(), NaverProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		User naverUser = new User();
		naverUser.setUsername(naverProfile.getResponse().getEmail()+"_naver");
		naverUser.setPassword(cosKey);
		naverUser.setOauth("naver");
		
		User originUser = userService.joinCheck(naverUser.getUsername());
		
		//??????????????? ??????
		if(originUser == null) {
			userService.join(naverUser);
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(naverUser.getUsername(), cosKey));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} else {
			//???????????? ????????? ??????
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(naverUser.getUsername(), cosKey));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
					
		return "redirect:/";
	}
	
	//?????? ????????? ??????
	@GetMapping("/auth/google/callback")
	public String googleCallback(String code) {
		
		RestTemplate rt = new RestTemplate();
		
		//header????????????
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//body????????????
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("client_id", "client_id");
		params.add("client_secret", "client_secret");
		params.add("code", code);
		params.add("grant_type", "authorization_code");
		params.add("redirect_uri", "http://localhost:8000/auth/google/callback");
		
		HttpEntity<MultiValueMap<String, String>> googleTokenRequest = 
				new HttpEntity<>(params, headers);
		
		//POST ???????????? ???????????? String???????????? ??????
		ResponseEntity<String> response = rt.exchange(
				"https://oauth2.googleapis.com/token",
				HttpMethod.POST,
				googleTokenRequest,
				String.class
		);
		
		ObjectMapper obMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			 oauthToken = obMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		//POST ???????????? key=value ???????????? ??????
		RestTemplate rt2 = new RestTemplate();
		
		//header????????????
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//????????? ????????? ????????? ??????????????? ??????
		HttpEntity<MultiValueMap<String, String>> googleProfileRequest = 
				new HttpEntity<>(headers2);
		
		//POST ???????????? ???????????? String???????????? ??????
		ResponseEntity<String> response2 = rt2.exchange(
				"https://www.googleapis.com/oauth2/v3/tokeninfo",
				HttpMethod.POST,
				googleProfileRequest,
				String.class
		);
		
		System.out.println(response2.getBody());
		
		ObjectMapper obMapper2 = new ObjectMapper();
		GoogleProfile googleProfile = null;
		
		try {
			googleProfile = obMapper2.readValue(response2.getBody(), GoogleProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
		
		User googleUser = new User();
		googleUser.setUsername(googleProfile.getEmail()+"_google");
		googleUser.setPassword(cosKey);
		googleUser.setOauth("google");
		
		User originUser = userService.joinCheck(googleUser.getUsername());
		
		//??????????????? ??????
		if(originUser == null) {
			userService.join(googleUser);
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(googleUser.getUsername(), cosKey));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} else {
			//???????????? ????????? ??????
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(googleUser.getUsername(), cosKey));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
					
		return "redirect:/";
	}
	
	//???????????? ????????? ??????
	@GetMapping("/auth/facebook/callback")
	public String facebookCallback(String code) {
		
		RestTemplate rt = new RestTemplate();
		
		//header????????????
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//body????????????
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("client_id", "client_id");
		params.add("redirect_uri", "http://localhost:8000/auth/facebook/callback");
		params.add("client_secret", "client_secret");
		params.add("code", code);
		
		HttpEntity<MultiValueMap<String, String>> facebookTokenRequest = 
				new HttpEntity<>(params, headers);
		
		//POST ???????????? ???????????? String???????????? ??????
		ResponseEntity<String> response = rt.exchange(
				"https://graph.facebook.com/v11.0/oauth/access_token",
				HttpMethod.POST,
				facebookTokenRequest,
				String.class
		);
		
		ObjectMapper obMapper = new ObjectMapper();
		OAuthToken oauthToken = null;
		try {
			 oauthToken = obMapper.readValue(response.getBody(), OAuthToken.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		//POST ???????????? key=value ???????????? ??????
		RestTemplate rt2 = new RestTemplate();
		
		//header????????????
		HttpHeaders headers2 = new HttpHeaders();
		headers2.add("Authorization", "Bearer " + oauthToken.getAccess_token());
		headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		
		//????????? ????????? ????????? ??????????????? ??????
		HttpEntity<MultiValueMap<String, String>> facebookProfileRequest = 
				new HttpEntity<>(headers2);
		
		//POST ???????????? ???????????? String???????????? ??????
		ResponseEntity<String> response2 = rt2.exchange(
				"https://graph.facebook.com/me",
				HttpMethod.GET,
				facebookProfileRequest,
				String.class
		);
		
		ObjectMapper obMapper2 = new ObjectMapper();
		FacebookProfile facebookProfile = null;
		
		try {
			facebookProfile = obMapper2.readValue(response2.getBody(), FacebookProfile.class);
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		
		
		User facebookUser = new User();
		facebookUser.setUsername(facebookProfile.getId()+"_facebook");
		facebookUser.setPassword(cosKey);
		facebookUser.setOauth("facebook");
		
		User originUser = userService.joinCheck(facebookUser.getUsername());
		
		//??????????????? ??????
		if(originUser == null) {
			userService.join(facebookUser);
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(facebookUser.getUsername(), cosKey));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		} else {
			//???????????? ????????? ??????
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(facebookUser.getUsername(), cosKey));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
					
		return "redirect:/";
	}
	
	//????????? ?????? ????????? ?????? ??????
	@GetMapping("/auth/findidForm")
	public String findId() {
		return "user/findidForm";
	}
	
	//???????????? ?????? ????????? ?????? ??????
	@GetMapping("/auth/findpwForm")
	public String findPassword() {
		return "user/findpwForm";
	}
	
	//????????? ?????? ????????? ?????? ??? ?????? ?????????
	@GetMapping("/auth/findid/{email}")
	public String findUsername(@PathVariable String email, Model model) {
		System.out.println("?????????");
		System.out.println(email);
		
		List<User> userList = userService.findByEmail(email);
		model.addAttribute("userList", userList);
		
		return "user/showuseridForm";
	}
	
	//???????????? ?????? ????????? ?????? ??? ????????? ?????? ??????
	@GetMapping("/auth/findpwForm2")
	public String findPassword2() {
		return "user/findpwForm2";
	}
}
