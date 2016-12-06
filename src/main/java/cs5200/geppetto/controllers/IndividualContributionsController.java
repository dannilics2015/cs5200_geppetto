package cs5200.geppetto.controllers;

import cs5200.geppetto.dao.CandidateDao;
import cs5200.geppetto.dao.CommitteesDao;
import cs5200.geppetto.dao.IndividualContributionsDao;
import cs5200.geppetto.model.Candidate;
import cs5200.geppetto.model.Committees;
import cs5200.geppetto.model.IndividualContributions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

@Controller
public class IndividualContributionsController {
    private static final String contribIDPathVariable =
            "/contribID/{contribID}";

    public static final String baseUrl = "/IndividualContributions";
	static Logger log = Logger.getLogger(WelcomeController.class.getName());

	@Autowired
	private IndividualContributionsDao individualContributionsDao;

	@Autowired
	private CommitteesDao committeesDao;

	@Autowired
	private CandidateDao candidateDao;

	// would be much better to do this with the inital join, but for dev time sake this works
	private void addRecipName(List<IndividualContributions> individualContributionsList)  throws
			SQLException {
		for(IndividualContributions individualContributions: individualContributionsList) {
			Candidate candidate =
					candidateDao.getCandidateByCID(individualContributions.getRecipId());
			individualContributions.setRecipName(candidate == null ? "" : candidate.getFirstLastP());
		}
		for(IndividualContributions individualContributions: individualContributionsList) {
			if(individualContributions.getRecipName().isEmpty()) {
				Committees cmte =
						committeesDao.getCommitteeByCmteId(individualContributions.getRecipId());
				individualContributions.setRecipName(cmte == null ? "" : cmte.getPACShort());
			}
		}
	}

    @GetMapping(baseUrl + contribIDPathVariable)
	public String index(Map<String, Object> model,
						@PathVariable(value="contribID") String contribID) throws SQLException {
		List<IndividualContributions> individualContributionsList =
				individualContributionsDao.get(contribID);
        addRecipName(individualContributionsList);
		model.put("individualContributionsList", individualContributionsList);
		return "individualContributions/index";
	}
}
