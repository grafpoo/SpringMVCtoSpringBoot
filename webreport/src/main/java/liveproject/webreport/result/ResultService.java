package liveproject.webreport.result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResultService {
	
	@Autowired
	private ResultRepository resultRepository;

	@Transactional
	public Result save(Result result) {
		resultRepository.save(result);
		return result;
	}

	public Map<String, Object> aggregateSeason(int year) {
		Map<String, Object> season = new HashMap<>();
		for ( Result result: resultRepository.findAll() ) {

		}
		return season;
	}
}
