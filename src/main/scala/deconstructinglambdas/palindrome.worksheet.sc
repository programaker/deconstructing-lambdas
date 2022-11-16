import deconstructinglambdas.functions.isPalindrome
import deconstructinglambdas.JSFunc

val isPalindromeFn = isPalindrome[Function]
isPalindromeFn("evilolive")
isPalindromeFn("turkey")

val isPalindromeJs = isPalindrome[JSFunc]
isPalindromeJs.renderJs
