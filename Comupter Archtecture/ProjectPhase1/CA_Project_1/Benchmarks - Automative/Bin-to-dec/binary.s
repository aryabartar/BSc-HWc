	.file	"binary.c"
	.section	.rodata
.LC0:
	.string	"cls"
.LC1:
	.string	"Enter the number to convert: "
.LC2:
	.string	"%d"
.LC3:
	.string	"\nBinary of Given Number is="
	.text
	.globl	main
	.type	main, @function
main:
.LFB5:
	.cfi_startproc
	pushq	%rbp
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp
	.cfi_def_cfa_register 6
	subq	$64, %rsp
	movq	%fs:40, %rax
	movq	%rax, -8(%rbp)
	xorl	%eax, %eax
	leaq	.LC0(%rip), %rdi
	call	system@PLT
	leaq	.LC1(%rip), %rdi
	movl	$0, %eax
	call	printf@PLT
	leaq	-56(%rbp), %rax
	movq	%rax, %rsi
	leaq	.LC2(%rip), %rdi
	movl	$0, %eax
	call	__isoc99_scanf@PLT
	movl	$0, -52(%rbp)
	jmp	.L2
.L3:
	movl	-56(%rbp), %eax
	cltd
	shrl	$31, %edx
	addl	%edx, %eax
	andl	$1, %eax
	subl	%edx, %eax
	movl	%eax, %edx
	movl	-52(%rbp), %eax
	cltq
	movl	%edx, -48(%rbp,%rax,4)
	movl	-56(%rbp), %eax
	movl	%eax, %edx
	shrl	$31, %edx
	addl	%edx, %eax
	sarl	%eax
	movl	%eax, -56(%rbp)
	addl	$1, -52(%rbp)
.L2:
	movl	-56(%rbp), %eax
	testl	%eax, %eax
	jg	.L3
	leaq	.LC3(%rip), %rdi
	movl	$0, %eax
	call	printf@PLT
	subl	$1, -52(%rbp)
	jmp	.L4
.L5:
	movl	-52(%rbp), %eax
	cltq
	movl	-48(%rbp,%rax,4), %eax
	movl	%eax, %esi
	leaq	.LC2(%rip), %rdi
	movl	$0, %eax
	call	printf@PLT
	subl	$1, -52(%rbp)
.L4:
	cmpl	$0, -52(%rbp)
	jns	.L5
	movl	$0, %eax
	movq	-8(%rbp), %rcx
	xorq	%fs:40, %rcx
	je	.L7
	call	__stack_chk_fail@PLT
.L7:
	leave
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE5:
	.size	main, .-main
	.ident	"GCC: (Ubuntu 7.2.0-8ubuntu3.2) 7.2.0"
	.section	.note.GNU-stack,"",@progbits
