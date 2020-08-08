package com.creditsaison.trippletsum;

import com.creditsaison.parser.JsonParserUtils;
import com.creditsaison.tripletsum.vo.TripletHistoryVO;
import com.creditsaison.tripletsum.vo.TripletSumVO;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.inject.Inject;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

public class TripletSumController extends Controller{

	
	private TripletSumService trippletSumService;
	
	@Inject
	public TripletSumController(TripletSumService trippletSumService) {
		this.trippletSumService = trippletSumService;
	}



	public Result getTripplet(Http.Request request) {
		JsonNode asJson = request.body().asJson();
		TripletSumVO fromJson = JsonParserUtils.fromJson(asJson, TripletSumVO.class);
		TripletSumVO trippletSumVO = trippletSumService.getTripletSum(fromJson);
		return ok(JsonParserUtils.toJson(trippletSumVO)).as(Http.MimeTypes.JSON);
	}
	
	public Result getTripletHistory(Http.Request request) {
		TripletHistoryVO trippletSumHistory = trippletSumService.getTripletSumHistory();
		return ok(JsonParserUtils.toJson(trippletSumHistory)).as(Http.MimeTypes.JSON);
	}
}
