package com.works.project

import com.works.project.data.remote.TodoApiTest
import com.works.project.domain.utils.ValidationsTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    TodoApiTest::class,
    ValidationsTest::class
)
class UnitTestSuite