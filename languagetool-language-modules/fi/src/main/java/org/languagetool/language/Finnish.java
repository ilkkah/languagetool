package org.languagetool.language;

import org.languagetool.Language;
import org.languagetool.UserConfig;
import org.languagetool.rules.Rule;
import org.languagetool.rules.patterns.AbstractPatternRule;
import org.languagetool.tagging.fi.DemoTagger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Finnish extends Language {

    @Override
    public String getName() {
        return "Finnish";
    }

    @Override
    public String getShortCode() {
        return "fi";
    }

    @Override
    public String[] getCountries() {
        return new String[]{"FI"};  // Finland
    }

    @Override
    public DemoTagger createDefaultTagger() {
      return new DemoTagger();
    }

    @Override
    public List<Rule> getRelevantRules(ResourceBundle messages, UserConfig userConfig, Language motherTongue, List<Language> altLanguages) throws IOException {
        return new ArrayList<>();  // Return empty list as we don't want any actual rules
    }

    @Override
    public List<AbstractPatternRule> getPatternRules() {
        return new ArrayList<>();  // Return empty list as we don't want any pattern rules
    }

    @Override
    public Contributor[] getMaintainers() {
        return new Contributor[] {
            new Contributor("Your Name")
        };
    }
}