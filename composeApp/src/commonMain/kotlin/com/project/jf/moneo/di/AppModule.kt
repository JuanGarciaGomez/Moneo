package com.project.jf.moneo.di

import com.project.jf.moneo.data.local.MoneoDatabase
import com.project.jf.moneo.data.local.repository.UserPreferencesRepository
import com.project.jf.moneo.data.local.repository.control_period.ControlPeriodRepository
import com.project.jf.moneo.data.local.repository.control_period.ControlPeriodRepositoryImpl
import com.project.jf.moneo.data.local.repository.transaction.TransactionRepository
import com.project.jf.moneo.data.local.repository.transaction.TransactionRepositoryImpl
import com.project.jf.moneo.domain.usecase.GetAllControlPeriodsUseCase
import com.project.jf.moneo.domain.usecase.GetOnboardingStatusUseCase
import com.project.jf.moneo.domain.usecase.GetTransactionsForPeriodUseCase
import com.project.jf.moneo.domain.usecase.GetUserNameUseCase
import com.project.jf.moneo.domain.usecase.SaveControlPeriodUseCase
import com.project.jf.moneo.domain.usecase.SaveHasCompletedOnboardingUseCase
import com.project.jf.moneo.domain.usecase.SaveUserNameUseCase
import com.project.jf.moneo.presentation.features.add_transaction.AddTransactionViewModel
import com.project.jf.moneo.presentation.features.home.HomeViewModel
import com.project.jf.moneo.presentation.features.first_period.FirstPeriodViewModel
import com.project.jf.moneo.presentation.features.onboarding.OnboardingViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val commonModule = module {
    factory { OnboardingViewModel(get()) }
    factory { FirstPeriodViewModel(get(), get(), get()) }
    factory { GetOnboardingStatusUseCase(get()) }
    factory { SaveHasCompletedOnboardingUseCase(get()) }
    factory { SaveUserNameUseCase(get()) }
    factory { GetUserNameUseCase(get()) }
    factory { SaveControlPeriodUseCase(get()) }
    factory { GetAllControlPeriodsUseCase(get()) }
    factory { GetTransactionsForPeriodUseCase(get()) }
    factory { HomeViewModel(get(), get()) }
    factory { AddTransactionViewModel() }
    single { UserPreferencesRepository() }
    single { get<MoneoDatabase>().controlPeriodDao() }
    single { get<MoneoDatabase>().transactionDao() }
    single<ControlPeriodRepository> { ControlPeriodRepositoryImpl(get()) }
    single<TransactionRepository> { TransactionRepositoryImpl(get()) }
}

expect fun platformModule(): Module