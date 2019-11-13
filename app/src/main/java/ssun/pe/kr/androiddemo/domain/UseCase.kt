package ssun.pe.kr.androiddemo.domain

abstract class UseCase<in P, out R> {

    abstract suspend fun execute(p: P): R
}