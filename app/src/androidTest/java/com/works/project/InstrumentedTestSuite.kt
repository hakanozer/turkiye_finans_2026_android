package com.works.project

import com.works.project.domain.utils.AppDatabaseTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    AppDatabaseTest::class
)
class InstrumentedTestSuite