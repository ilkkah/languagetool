package org.languagetool.rules.fi;

import org.languagetool.AnalyzedSentence;
import org.languagetool.AnalyzedTokenReadings;
import org.languagetool.Language;
import org.languagetool.UserConfig;
import org.languagetool.rules.Categories;
import org.languagetool.rules.RuleMatch;
import org.languagetool.rules.spelling.SpellingCheckRule;
import org.puimula.libvoikko.Voikko;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class FinnishSpellerRule extends SpellingCheckRule {
	private final Voikko voikko;

	public FinnishSpellerRule(ResourceBundle messages, Language language, UserConfig userConfig) throws Exception {
		super(messages, language, userConfig);
		voikko = new Voikko("fi");
		setCategory(Categories.TYPOS.getCategory(messages));
	}

	@Override
	public String getId() {
		return "FINNISH_SPELLER_RULE";
	}

	@Override
	public String getDescription() {
		return "Finnish spell checker using Voikko";
	}

	@Override
	public RuleMatch[] match(AnalyzedSentence sentence) throws IOException {
		List<RuleMatch> ruleMatches = new ArrayList<>();

		for (AnalyzedTokenReadings token : sentence.getTokensWithoutWhitespace()) {
			String word = token.getToken();

			if (word.length() == 0
					|| isUrl(word)
					|| isEMail(word)
					|| token.isImmunized()
					|| token.isIgnoredBySpeller()
					|| isNumberString(word)) {
				continue;
			}

			if (!voikko.spell(word)) {
				RuleMatch ruleMatch = new RuleMatch(this, sentence,
						token.getStartPos(), token.getEndPos(),
						"Possible spelling mistake",
						"Possible spelling mistake");
				ruleMatch.setSuggestedReplacements(voikko.suggest(word));
				ruleMatches.add(ruleMatch);
			}
		}

		return toRuleMatchArray(ruleMatches);
	}

	private boolean isNumberString(String word) {
		return word.matches("^[0-9]+$");
	}

	@Override
	public boolean isMisspelled(String word) {
		return !voikko.spell(word);
	}

	public void close() {
		if (voikko != null) {
			voikko.terminate();
		}
	}
}