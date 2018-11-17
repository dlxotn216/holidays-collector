package me.holiday.collector.application.controller;

import me.holiday.collector.domain.NationCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.Rendering;

import java.util.Arrays;

/**
 * @author Lee Tae Su
 * @version 1.0
 * @project crawler
 * @since 2018-10-17
 * 
 * API 호출하는 화면을 위한 Controller
 */
@Controller
public class HolidayCollectingController {
	
	@GetMapping("/")
	public Rendering indexPage(Model model){
		model.addAttribute("nationCodes", NationCode.values());
		model.addAttribute("mediaTypes", Arrays.asList(MediaType.APPLICATION_JSON));
		
		return Rendering.view("index").build();
	}
}
