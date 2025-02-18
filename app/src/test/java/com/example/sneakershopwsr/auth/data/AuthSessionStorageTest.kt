package com.example.sneakershopwsr.auth.data

import android.content.SharedPreferences
import com.example.sneakershopwsr.auth.domain.AuthInfo
import com.example.sneakershopwsr.core.domain.SessionStorage
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.serialization.json.Json
import org.junit.Before


/**
 * Тест на [AuthSessionStorage]
 **/
@OptIn(ExperimentalCoroutinesApi::class)
class AuthSessionStorageTest {
    private val sharedPreferences: SharedPreferences = mockk {
        every { getString(MOCK_KEY, null) } returns MOCK_VALUE
    }
    private val converter: AuthInfoConverter = mockk{
        every {
            convertFromTo(any())
        } returns AuthInfo(MOCK_AUTH_ID)
    }
    private lateinit var dispatcher: TestDispatcher
    private lateinit var storage: SessionStorage<AuthInfo>


    @Before
    fun setUp() {
        dispatcher = StandardTestDispatcher(TestCoroutineScheduler())
        storage = AuthSessionStorage(
            sharedPreferences = sharedPreferences,
            converter = converter,
            dispatcher = dispatcher,
        )
        Dispatchers.setMain(dispatcher)
    }


    @org.junit.Test
    fun `test get`() = runTest {
        mockkStatic(Json::class)

        val actual = storage.get(MOCK_KEY)
        val expected = AuthInfo(MOCK_AUTH_ID)
        Truth.assertThat(actual).isEqualTo(expected)
    }

    companion object {
        const val MOCK_KEY = "mock_key"
        const val MOCK_VALUE = "{\"id\": \"mock_value\"}"
        const val MOCK_AUTH_ID = "1"
    }
}