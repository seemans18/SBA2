package com.eval.coronakit.controller;



import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.eval.coronakit.entity.ProductMaster;
import com.eval.coronakit.exception.CoronakitException;
import com.eval.coronakit.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	ProductService productService;
	
	//List<ProductMaster> allProducts = new ArrayList<>();
	
	@GetMapping("/home")
	public String home() {
		return "admin-home";
	}
	
	@GetMapping("/product-entry")
	public String productEntry(Model model) {
		ProductMaster product = new ProductMaster();
		model.addAttribute("product", product);
		return "add-new-item";
	}
	
	@SuppressWarnings("null")
	@PostMapping("/product-save")
	public String productSave(@ModelAttribute("product") @Valid ProductMaster product, BindingResult result) throws CoronakitException {
		String view = "";
		if(result.hasErrors()) {
			view = "add-new-item";
		}else {
			productService.addNewProduct(product);
			view = "redirect:/admin/product-list";
		}
		return view;
		//allProducts.add(product);
		//model.addAttribute("products", productService.getAllProducts());
		//model.addAttribute("msg", "Product added Succesfully!!");
		//return "show-all-item-admin";
	}
	

	@GetMapping("/product-list")
	public String productList(Model model) throws CoronakitException {
		model.addAttribute("products", productService.getAllProducts());
		return "show-all-item-admin";
	}
	
	@GetMapping("/product-delete/{productId}")
	public String productDelete(@PathVariable("productId") int productId) throws CoronakitException {
		String view = "";
		productService.deleteProduct(productId);
		view = "redirect:/admin/product-list";
		return view;
		
		/*model.addAttribute("products", productService.getAllProducts());
		model.addAttribute("msg", "Product deleted Succesfully!!");
		return "show-all-item-admin";*/
	}
	
}
