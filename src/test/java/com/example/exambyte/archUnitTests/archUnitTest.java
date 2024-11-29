package com.example.exambyte.archUnitTests;

import com.example.exambyte.ExamByteApplication;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;

@AnalyzeClasses(packagesOf = ExamByteApplication.class, importOptions = ImportOption.DoNotIncludeTests.class)
public class archUnitTest {
    private final JavaClasses classes = new ClassFileImporter().importClasses(ExamByteApplication.class);
}
