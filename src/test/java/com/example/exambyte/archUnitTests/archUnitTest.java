package com.example.exambyte.archUnitTests;

import com.example.exambyte.ExamByteApplication;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import org.springframework.stereotype.Controller;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.fields;
import static com.tngtech.archunit.library.Architectures.onionArchitecture;
import static com.tngtech.archunit.library.plantuml.rules.PlantUmlArchCondition.Configuration.consideringOnlyDependenciesInDiagram;
import static com.tngtech.archunit.library.plantuml.rules.PlantUmlArchCondition.adhereToPlantUmlDiagram;

@AnalyzeClasses(packagesOf = ExamByteApplication.class, importOptions = ImportOption.DoNotIncludeTests.class)
public class archUnitTest {

    @ArchTest
    ArchRule onionTest = onionArchitecture()
            .applicationServices("..application.service..")
            .domainServices("..domain.service..")
            .domainModels("..domain.model..")
            .adapter("web", "..controllers..")
            .adapter("config", "..configuration..")
            .adapter("persistence", "..repositories..");

    @ArchTest
    static ArchRule umlTest = classes().should(adhereToPlantUmlDiagram(
            ExamByteApplication.class.getResource("/architecture.puml"),
            consideringOnlyDependenciesInDiagram()
    ));

    @ArchTest
    ArchRule allFieldsInControllersShouldBePrivate = fields()
            .that()
            .areDeclaredInClassesThat()
            .areAnnotatedWith(Controller.class)
            .should()
            .bePrivate();

}
