package com.eval.coronakit.controller;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.eval.coronakit.entity.CoronaKit;
import com.eval.coronakit.entity.KitDetail;
import com.eval.coronakit.entity.ProductMaster;
import com.eval.coronakit.exception.CoronakitException;
import com.eval.coronakit.service.CoronaKitService;
import com.eval.coronakit.service.KitDetailService;
import com.eval.coronakit.service.ProductService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CoronaKitService coronaKitService;
	
	@Autowired
	KitDetailService kitDetailService;
	
	List<KitDetail> kitDetail = new ArrayList<>();
	
	@Autowired
	private HttpSession session;
	
	@RequestMapping("/home")
	public String home() {
		return "user-home";
	}
	
	@RequestMapping("/show-kit")
	public String showKit(Model model) throws CoronakitException {
		int totalAmt = 0;
		List<String> pNames = new ArrayList<String>();
		
		for(KitDetail k:kitDetail) {
			totalAmt += k.getAmount() * k.getQuantity();
			pNames.add(productService.getProductById(k.getProductId()).getProductName());
		}
		model.addAttribute("kitDetail", kitDetail);
		model.addAttribute("totalAmt", totalAmt);
		model.addAttribute("productNames", pNames);
		return "show-cart";
	}

	@RequestMapping("/show-list")
	public String showList(Model model) throws CoronakitException {
		model.addAttribute("products", productService.getAllProducts());
		return "show-all-item-user";
	}
	
	@RequestMapping("/add-to-cart/{productId}")
	public String showKit(@Valid @PathVariable("productId") int productId) throws CoronakitException {
		String view = "";
		boolean prodExistsFlag = false;
		int index = 0;
		KitDetail k = new KitDetail();
		k.setProductId(productId);
		k.setAmount(productService.getProductById(productId).getCost());
		//k.setQuantity(qty);
		for(KitDetail k1:kitDetail) {
			if(k1.getProductId() == k.getProductId()) {
				k.setQuantity(k1.getQuantity()+1);
				index = kitDetail.indexOf(k1);
				prodExistsFlag = true;
				break;
			}
		}
		if(!prodExistsFlag) {
			k.setQuantity(1);
			kitDetail.add(k);
		}else {
			kitDetail.set(index, k);
		}
		//model.addAttribute("msg","Product " + productService.getProductById(productId).getProductName() + "added to Kit succesfully!");
		view = "redirect:/user/show-kit";
		return view;
	}
	
	@RequestMapping("/checkout")
	public String checkout(Model model) {
		//model.addAttribute("address", address);
		return "checkout-address";
	}
	
	@RequestMapping("/finalize")
	public String finalizeOrder(@RequestParam("address") String address,Model model) throws CoronakitException {
		int totalAmt = 0;
		List<String> pNames = new ArrayList<String>();
		for(KitDetail k:kitDetail) {
			totalAmt += k.getAmount() * k.getQuantity();
			pNames.add(productService.getProductById(k.getProductId()).getProductName());
		}
		
		CoronaKit coronaKit = new CoronaKit();
		coronaKit.setDeliveryAddress(address);
		coronaKit.setOrderDate(LocalDateTime.now().toString());
		coronaKit.setTotalAmount(totalAmt);
				
		coronaKitService.saveKit(coronaKit);
		
		for(KitDetail k:kitDetail) {
			k.setCoronaKitId(coronaKit.getId());
			kitDetailService.addKitItem(k);	
		}
		
		model.addAttribute("coronaKit", coronaKit);
		model.addAttribute("kitDetail", kitDetailService.getAllKitItemsOfAKit(coronaKit.getId()));
		model.addAttribute("productNames", pNames);
		return "show-summary";
	}
	
	@RequestMapping("/delete/{itemId}")
	public String deleteItem(@PathVariable("itemId") int itemId, ModelMap model) throws CoronakitException {
		String view="";
		kitDetail.remove(itemId);
		model.addAttribute("msg","Product deleted from the Kit succesfully!");
		view = "redirect:/user/show-kit";
		return view;
	}
}
