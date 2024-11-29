package com.example.exambyte.archUnitTests;

import com.example.exambyte.ExamByteApplication;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.onionArchitecture;

@AnalyzeClasses(packagesOf = ExamByteApplication.class, importOptions = ImportOption.DoNotIncludeTests.class)
public class archUnitTest {
    private final JavaClasses classes = new ClassFileImporter().importClasses(ExamByteApplication.class);

    @ArchTest
    ArchRule onionTest = onionArchitecture()
            .applicationServices("..application.service..")
            .domainServices("..domain.service..")
            .domainModels("..domain.model..")
            .adapter("web", "..controllers..")
            .adapter("config", "..configuration..")
            .adapter("persistence", "..repositories..");
}
