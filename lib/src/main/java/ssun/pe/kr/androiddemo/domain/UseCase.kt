package ssun.pe.kr.androiddemo.domain

abstract class UseCase<in P, out R> {

    abstract fun execute(p: P): R
}