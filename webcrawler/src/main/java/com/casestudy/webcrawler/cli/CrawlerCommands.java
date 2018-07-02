package com.casestudy.webcrawler.cli;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.casestudy.webcrawler.core.Launcher;

import sshd.shell.springboot.autoconfiguration.SshdShellCommand;
import sshd.shell.springboot.console.ConsoleIO;

@Component
@SshdShellCommand(value = "webcrawler", description = "a simple web crawler. Type 'webcrawler help' for supported subcommands")
public class CrawlerCommands {

    @SshdShellCommand(value = "crawl", description = "parse a website for site map creation")
    public String crawl(String siteURL) throws IOException {
        ConsoleIO.writeOutput("Need user info");
        String response = Launcher.launch(siteURL);
       
        return response;
    }

  
}