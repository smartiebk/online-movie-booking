package com.ombp.cloud.app.ui.api;

import java.io.IOException;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ombp.cloud.constants.EndPointConstants;
import com.ombp.cloud.model.alerts.EmailLog;
import com.ombp.cloud.model.bs.Movie;
import com.ombp.cloud.model.bs.MovieShow;
import com.ombp.cloud.model.bs.Screen;
import com.ombp.cloud.model.bs.SearchCriteria;
import com.ombp.cloud.model.bs.Theatre;
import com.ombp.cloud.model.usermanagement.SMSLog;

@FeignClient(contextId = "openAuthServer", value = "openAuthServer", url = "${authservice.hostname}")
public interface OpenAuthServerAPI extends EndPointConstants {

	@RequestMapping(value = "/log/sms/save", method = RequestMethod.POST)
	SMSLog logSMS(@RequestBody SMSLog smsLog) throws IOException;
	
	@RequestMapping(value = "/log/email/save", method = RequestMethod.POST)
	EmailLog logEmail(@RequestBody EmailLog emailLog) throws IOException;
	
	
	@RequestMapping(value = "/open/save/movieshow", method = RequestMethod.POST)
	public MovieShow saveMovieShow(@RequestBody MovieShow movieShow) throws IOException;
	
	@RequestMapping(value = "/open/fetch/moviebyname/{movieName}", method = RequestMethod.GET)
	public Movie fetchMovieByMovieName(@PathVariable("movieName") String movieName) throws IOException;
	
	@RequestMapping(value = "/open/fetch/screens/{theatreId}", method = RequestMethod.GET)
	public List<Screen> fetchAllScreensByTheatreId(@PathVariable("theatreId") Integer theatreId) throws IOException;
	
	@RequestMapping(value = "/open/fetch/shows/{theatreId}", method = RequestMethod.GET)
	public List<MovieShow> fetchAllShowsByTheatreId(@PathVariable("theatreId") Integer theatreId) throws IOException;
	
	@RequestMapping(value = "/open/search/theatres", method = RequestMethod.POST)
	public List<Theatre> fetchAllTheatreBySearchCriteria(@RequestBody SearchCriteria searchCriteria) throws IOException;
	
	@RequestMapping(value = "/open/delete/movieshow", method = RequestMethod.POST)
	public MovieShow deleteMovieShow(@RequestBody MovieShow movieShow) throws IOException;
	
}
