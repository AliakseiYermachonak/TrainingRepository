package com.epam.task.two.text.parser;

import java.util.ArrayList;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.epam.task.two.text.entity.Component;
import com.epam.task.two.text.entity.LeafComponent;
import com.epam.task.two.text.entity.TextComponent;
import com.epam.task.two.text.executor.RegexSupplier;

/**
 * Parser class for the following links of the
 * chain of responsibility pattern. 
 * @author Alexey Yermachyonok
 * @version 1.0
 */

public class ParagraphParser implements Parser {

    private Parser nextParser;
    private static final Logger LOGGER = Logger.getLogger(ParagraphParser.class);
    
    public ParagraphParser() {

    }

    /**
     * Use this to link the next chain unit.
     * @param Parser
     * @return Parser
     */
    @Override
    public void setNextParser(Parser parser) {
        nextParser = parser;
    }

    /**
     * Use this for parsing the text into the elements
     * of the given text type.
     * @param text to parse
     * @return ArrayList of the components of the parsed text.
     * @see Component
     */
    @Override
    public ArrayList<Component> parse(String text) {
        ArrayList<Component> list = new ArrayList<>();

        Pattern pattern = Pattern.compile(RegexSupplier.getRegex("PARAGRAPH"));
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String temp = matcher.group().trim();
            LOGGER.debug("Trying to add SENTENCE || " + temp);
            LOGGER.debug("To PARAGRAPH");
            if (temp.matches(RegexSupplier.getRegex("DigitWithDot"))) {
                list.add(new LeafComponent(temp));
            } else {
                list.add(new TextComponent(nextParser.parse(temp)));
                LOGGER.debug("New component SENTENCE  \"" + temp + "\" addead to PARAGRAPH");
            }
        }
        return list;
    }
}
