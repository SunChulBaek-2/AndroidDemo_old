package ssun.pe.kr.androiddemo.domain

abstract class UseCase<in P, out R> {

    abstract operator fun invoke(p: P): R
}