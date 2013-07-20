package org.classification.util;

import static org.classification.util.ClassificationUtil.COMMERCIAL;
import static org.classification.util.ClassificationUtil.NONCOMMERCIAL;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.google.common.collect.Lists;

public final class ClassificationData {

    private ClassificationData() {
        throw new AssertionError();
    }

    // API

    public static List<ImmutablePair<String, String>> commercialAndNonCommercialTweets() {
        final List<ImmutablePair<String, String>> noncommercialTweets = nonCommercialTweets_batch1();
        final List<ImmutablePair<String, String>> commercialTweets = commercialTweets_batch1();
        final List<ImmutablePair<String, String>> allTweets = Lists.newArrayList();
        allTweets.addAll(noncommercialTweets);
        allTweets.addAll(commercialTweets);
        Collections.shuffle(allTweets);
        return allTweets;
    }

    // util

    @SuppressWarnings("unchecked")
    private static List<ImmutablePair<String, String>> commercialTweets_batch1() {
        final List<ImmutablePair<String, String>> commercialTweets = Lists.newArrayList(// @formatter:off
            new ImmutablePair<String, String>(COMMERCIAL,"We're looking to #hire a Front End Developer/Creative Designer to join our team in Leeds. Get in touch for more information."), 
            new ImmutablePair<String, String>(COMMERCIAL,"New job Nurse Practitioners & Physician Assistants - New Jersey  #hire #jobs"), 
            new ImmutablePair<String, String>(COMMERCIAL,"Looking to hire excellent senior Java developers preferrably with MongoDb skills to our Espoo, Finland office #agile #hire #Java #MongoDb"), 
            new ImmutablePair<String, String>(COMMERCIAL,"I'm looking for #scala dev #contract in #Melbourne or #Sydney. #job"), 
            new ImmutablePair<String, String>(COMMERCIAL,"Scala Developer with World-Class Tech Co in Cambridge #job #scala #cambridge"),  
            new ImmutablePair<String, String>(COMMERCIAL,"Hiring a C# or Java Software Developer / Programmer - London in London, United Kingdom http://bull.hn/l/119NU/3  #job #.net #Java"),  
            new ImmutablePair<String, String>(COMMERCIAL,"We have lots of #java engineer #job opportunities available! http://bit.ly/10b6Oap"),  
            new ImmutablePair<String, String>(COMMERCIAL,"Know anyone for this job? JAVA/OLYMPIC consultant, german fluent in Luxembourg City, Luxembourg http://bull.hn/l/YWLX/5  #job #java"),  
            new ImmutablePair<String, String>(COMMERCIAL,"Looking for a Senior Java Developer in Hoboken, NJ http://bull.hn/l/12MHI/  #job #java"), 
            new ImmutablePair<String, String>(COMMERCIAL,"Know anyone for this job? Sr. Java Software Engineer in Atlanta, GA http://bull.hn/l/12D6I/5  #job #java"),  
            new ImmutablePair<String, String>(COMMERCIAL,"Are you a good fit for this job? Java Backend Developer in Amsterdam, Netherlands http://bull.hn/l/XVTE/6  #job #java #amsterdam"), 
            new ImmutablePair<String, String>(COMMERCIAL,"Hire Experienced #WordPress developer at Affordable price, Inquire Now! http://www.valuecoders.com/hire-developers/hire-wordpress-developers"),
            new ImmutablePair<String, String>(COMMERCIAL,"IQTELL is seeking iOS and java developers in NJ and NYC Area http://iqtell.com/careers/experienced-java-developers/ … #iOS #dev #job #java #hire #jobsearch #developers #apps"),
            new ImmutablePair<String, String>(COMMERCIAL,"Need Java Developers and .Net/Sharepoint developers. Multiple positions. http://bit.ly/aKiIZG #Java #.net #sharepoint #hire #job #developers"),
            new ImmutablePair<String, String>(COMMERCIAL,"Need To Hire JS Expert. Simple Work #hiring #java #site #work #simple #expert #hire #job http://bit.ly/1i9avN"),
            new ImmutablePair<String, String>(COMMERCIAL,"HOT Requirement! Sr. Java/J2EE Dev | Hyderabad, IN | Start-ASAP |. http://bit.ly/GCeuH #job #india #java #j2ee #naukri #jobs #hire #hiring"),
            new ImmutablePair<String, String>(COMMERCIAL,"Sr. Software Developer | Start-ASAP | http://bit.ly/dSYxo8 #job #kabul #java #j2ee #php #jobs #hire #hiring #web #developer | HTML PHP AJAX"),
            new ImmutablePair<String, String>(COMMERCIAL,"We are hiring:- http://tuenti.com/jobs/ #job #jobs #hiring #hire #frontend #backend #java #javascript #php #php5 #AJAX #LAMP #designer #IT"),
            new ImmutablePair<String, String>(COMMERCIAL,"Architect Spring Framework Job (Cary, NC) http://zillionjobs.com/Job-8601476.html … #Spring #Framework #job #jobs #Cary"),
            new ImmutablePair<String, String>(COMMERCIAL,"#job Software Engineer - Java, Spring Framework, JUnit – Bristol: SW-Bristol, Software Engineer - Java, Spring... http://bit.ly/149CeGu"),
            new ImmutablePair<String, String>(COMMERCIAL,"New #Job: Development Work For Web Application In SPRING Framework http://bit.ly/112C8fR"),
            new ImmutablePair<String, String>(COMMERCIAL,"#Job: WebSphere Developer - CD-websphere - WebSphere Developer- WebSphere, spring Framework, MVC... http://is.gd/kfzg40  #telecommute"),
            new ImmutablePair<String, String>(COMMERCIAL,"#java #job - Sr Java Resource - Spring Framework java script jbpm Java E... ($1,000 - 5,000) - http://donanza.com/t/8900225  #jobs"),
            new ImmutablePair<String, String>(COMMERCIAL,"#job #freelance Java writers for Spring framework - repost by hainasoft: I have a long term project which involv... http://adf.ly/GSVRf"),
            new ImmutablePair<String, String>(COMMERCIAL,"#Hiring for #job #careers JAVA Developers (SPRING FRAMEWORK) http://dlvr.it/2glzjQ  #jobs"),
            new ImmutablePair<String, String>(COMMERCIAL,"New #Job: Pair Programming Buddy Java Spring Framework http://bit.ly/STYzAx"),
            new ImmutablePair<String, String>(COMMERCIAL,"New #Job: Develop A Two Page Web Site On Spring Framework http://bit.ly/XDSuvk"),
            new ImmutablePair<String, String>(COMMERCIAL,"#jquery #job - 2EE technology, Spring Framework, web service , DHTML, CSS JavaScript, AJA... - http://donanza.com/t/7336100  #jobs"),
            new ImmutablePair<String, String>(COMMERCIAL,"#Job Web application by ancitiousManish: I have java web project developed using spring framework. After ad... http://bit.ly/XxL2jE  #XML"),
            new ImmutablePair<String, String>(COMMERCIAL,"Develop final recurring invoices module with final Spring Framework 3.x final Quartz scheduler… http://goo.gl/fb/Zrymb  #freelance #job"),
            new ImmutablePair<String, String>(COMMERCIAL,"http://OracleJobs.com  : Software EngineerJava, Spring Framework, Oracle databases at Azstatejobs (Phoenix, AZ... http://oraclejobs.me/TbGPlh  #job"),
            new ImmutablePair<String, String>(COMMERCIAL,"#final JOB Alert: final Seeking a Developer - Leading Edge http://ow.ly/dvYLy  Excellent knowledge using the Spring framework for web apps & CVS req"),
            new ImmutablePair<String, String>(COMMERCIAL,"Web Application Developer - Java/J2EE, Spring Framework, JavaScr - Tempe, AZ: Are final you a final Web Applica... http://bit.ly/St9fX9  #job #career"),
            new ImmutablePair<String, String>(COMMERCIAL,"final Java Developer #final job in Richardson http://ow.ly/cgsWI  Strong in Java5 programming (SCJP cert pref) & spring framework (MVC, IOC, JDBC, etc)"),
            new ImmutablePair<String, String>(COMMERCIAL,"#VMware final is looking for a final software engineer for final open source Spring final GemFire project http://j.mp/GVznyX  #springframework #job #java"),
            new ImmutablePair<String, String>(COMMERCIAL,"New #Job: Google final GWT And Spring final Framework Expert (Training And Advisor) http://bit.ly/rBSdDf"),
            new ImmutablePair<String, String>(COMMERCIAL,"New #Job: Java Web Development - Spring Framework http://bit.ly/otImRr")
        ); // @formatter:on
        return commercialTweets;
    }

    @SuppressWarnings("unchecked")
    private static List<ImmutablePair<String, String>> nonCommercialTweets_batch1() {
        final List<ImmutablePair<String, String>> noncommercialTweets = Lists.newArrayList(// @formatter:off
            new ImmutablePair<String, String>(NONCOMMERCIAL,"A set of great #scala and #akka examples http://bit.ly/KlJkro  #java #mapred #programming #dev"), 
            new ImmutablePair<String, String>(NONCOMMERCIAL,"New report shows that 11% of #Java devs also use #Scala on some projects http://0t.ee/devprodrep2012c"), 
            new ImmutablePair<String, String>(NONCOMMERCIAL,"What features of #java have been dropped in #scala? http://www.javacodegeeks.com/2011/08/what-features-of-java-have-been-dropped.html"), 
            new ImmutablePair<String, String>(NONCOMMERCIAL,"Why should a #Java #developer learn #Scala? To write better Java, says @jessitron: http://bit.ly/13ReUfU  via @JAXenter"), 
            new ImmutablePair<String, String>(NONCOMMERCIAL,"#Mixin in #Java with Aspects for a #Scala traits sample http://buff.ly/10nAOks"),
            new ImmutablePair<String, String>(NONCOMMERCIAL,"The Play Framework at LinkedIn #java #linkedin #scala http://es.slideshare.net/brikis98/the-play-framework-at-linkedin"), 
            new ImmutablePair<String, String>(NONCOMMERCIAL,"Why Scala is terser than Java? http://sortega.github.io/development/2013/06/22/terseness/ #Scala #Java"),
            new ImmutablePair<String, String>(NONCOMMERCIAL,"So @springrod 'says' there is more junk in #scala libs than in #java ones, I want to see evidence for that(45:10): http://www.parleys.com"),
            new ImmutablePair<String, String>(NONCOMMERCIAL,"What are the popular code conventions based on some @github hosted code ? http://sideeffect.kr/popularconvention/ (via @heyitsnoah)"),
            new ImmutablePair<String, String>(NONCOMMERCIAL,"Good Presentation. The #PlayFramework at #LinkedIn: Productivity and Performance at Scale: http://youtu.be/8z3h4Uv9YbE  #Scala #Java"),
            new ImmutablePair<String, String>(NONCOMMERCIAL,"Looking forward to #NexusLive today at 12PM EDT --- @jvanzyl is hopping on to talk about the latest Maven release! http://goo.gl/sUP19"),
            new ImmutablePair<String, String>(NONCOMMERCIAL,"Spring AMQP 1.2 Released - http://ow.ly/n2ZHP  #amqp #rabbitmq #nojms"),
            new ImmutablePair<String, String>(NONCOMMERCIAL,"Best way to get identity of inserted row? - http://stackoverflow.com/questions/42648/best-way-to-get-identity-of-inserted-row"),
            new ImmutablePair<String, String>(NONCOMMERCIAL,"Bash - A few commands to use again and again http://blog.trifork.com/2013/03/28/bash-a-few-commands-to-use-again-and-again/ … via @triforkams"),
            new ImmutablePair<String, String>(NONCOMMERCIAL,"Choosing a #Programming Language: Recruitment - #softwaredevelopment http://buff.ly/1dBtacM"),
            new ImmutablePair<String, String>(NONCOMMERCIAL,"Java's #Reflection API - #CoreJava http://goo.gl/bsWCB"),
            new ImmutablePair<String, String>(NONCOMMERCIAL,"ASCII Art Java example http://www.mkyong.com/java/ascii-art-java-example/ … via @mkyong")
        ); // @formatter:on
        return noncommercialTweets;
    }
    // API

}