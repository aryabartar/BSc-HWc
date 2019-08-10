	.file	"basicmath_small.c"
	.text
	.section	.rodata
	.align 8
.LC11:
	.string	"********* CUBIC FUNCTIONS ***********"
.LC12:
	.string	"Solutions:"
.LC13:
	.string	" %f"
	.align 8
.LC21:
	.string	"********* INTEGER SQR ROOTS ***********"
.LC22:
	.string	"sqrt(%3d) = %2d\n"
.LC23:
	.string	"\nsqrt(%lX) = %X\n"
	.align 8
.LC24:
	.string	"********* ANGLE CONVERSION ***********"
	.align 8
.LC27:
	.string	"%3.0f degrees = %.12f radians\n"
.LC29:
	.string	""
	.align 8
.LC30:
	.string	"%.12f radians = %3.0f degrees\n"
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
	subq	$208, %rsp
	movsd	.LC0(%rip), %xmm0
	movsd	%xmm0, -8(%rbp)
	movsd	.LC1(%rip), %xmm0
	movsd	%xmm0, -16(%rbp)
	movsd	.LC2(%rip), %xmm0
	movsd	%xmm0, -24(%rbp)
	movsd	.LC3(%rip), %xmm0
	movsd	%xmm0, -32(%rbp)
	movsd	.LC0(%rip), %xmm0
	movsd	%xmm0, -56(%rbp)
	movsd	.LC4(%rip), %xmm0
	movsd	%xmm0, -64(%rbp)
	movsd	.LC5(%rip), %xmm0
	movsd	%xmm0, -72(%rbp)
	movsd	.LC3(%rip), %xmm0
	movsd	%xmm0, -80(%rbp)
	movsd	.LC0(%rip), %xmm0
	movsd	%xmm0, -88(%rbp)
	movsd	.LC6(%rip), %xmm0
	movsd	%xmm0, -96(%rbp)
	movsd	.LC7(%rip), %xmm0
	movsd	%xmm0, -104(%rbp)
	movsd	.LC8(%rip), %xmm0
	movsd	%xmm0, -112(%rbp)
	movsd	.LC0(%rip), %xmm0
	movsd	%xmm0, -120(%rbp)
	movsd	.LC9(%rip), %xmm0
	movsd	%xmm0, -128(%rbp)
	movsd	.LC0(%rip), %xmm0
	movsd	%xmm0, -136(%rbp)
	movsd	.LC10(%rip), %xmm0
	movsd	%xmm0, -144(%rbp)
	movq	$1072497001, -152(%rbp)
	movq	$0, -160(%rbp)
	leaq	.LC11(%rip), %rdi
	call	puts@PLT
	leaq	-192(%rbp), %rdx
	leaq	-196(%rbp), %rax
	movsd	-32(%rbp), %xmm3
	movsd	-24(%rbp), %xmm2
	movsd	-16(%rbp), %xmm1
	movsd	-8(%rbp), %xmm0
	movq	%rdx, %rsi
	movq	%rax, %rdi
	call	SolveCubic@PLT
	leaq	.LC12(%rip), %rdi
	movl	$0, %eax
	call	printf@PLT
	movl	$0, -44(%rbp)
	jmp	.L2
.L3:
	movl	-44(%rbp), %eax
	cltq
	movsd	-192(%rbp,%rax,8), %xmm0
	leaq	.LC13(%rip), %rdi
	movl	$1, %eax
	call	printf@PLT
	addl	$1, -44(%rbp)
.L2:
	movl	-196(%rbp), %eax
	cmpl	%eax, -44(%rbp)
	jl	.L3
	movl	$10, %edi
	call	putchar@PLT
	leaq	-192(%rbp), %rdx
	leaq	-196(%rbp), %rax
	movsd	-80(%rbp), %xmm3
	movsd	-72(%rbp), %xmm2
	movsd	-64(%rbp), %xmm1
	movsd	-56(%rbp), %xmm0
	movq	%rdx, %rsi
	movq	%rax, %rdi
	call	SolveCubic@PLT
	leaq	.LC12(%rip), %rdi
	movl	$0, %eax
	call	printf@PLT
	movl	$0, -44(%rbp)
	jmp	.L4
.L5:
	movl	-44(%rbp), %eax
	cltq
	movsd	-192(%rbp,%rax,8), %xmm0
	leaq	.LC13(%rip), %rdi
	movl	$1, %eax
	call	printf@PLT
	addl	$1, -44(%rbp)
.L4:
	movl	-196(%rbp), %eax
	cmpl	%eax, -44(%rbp)
	jl	.L5
	movl	$10, %edi
	call	putchar@PLT
	leaq	-192(%rbp), %rdx
	leaq	-196(%rbp), %rax
	movsd	-112(%rbp), %xmm3
	movsd	-104(%rbp), %xmm2
	movsd	-96(%rbp), %xmm1
	movsd	-88(%rbp), %xmm0
	movq	%rdx, %rsi
	movq	%rax, %rdi
	call	SolveCubic@PLT
	leaq	.LC12(%rip), %rdi
	movl	$0, %eax
	call	printf@PLT
	movl	$0, -44(%rbp)
	jmp	.L6
.L7:
	movl	-44(%rbp), %eax
	cltq
	movsd	-192(%rbp,%rax,8), %xmm0
	leaq	.LC13(%rip), %rdi
	movl	$1, %eax
	call	printf@PLT
	addl	$1, -44(%rbp)
.L6:
	movl	-196(%rbp), %eax
	cmpl	%eax, -44(%rbp)
	jl	.L7
	movl	$10, %edi
	call	putchar@PLT
	leaq	-192(%rbp), %rdx
	leaq	-196(%rbp), %rax
	movsd	-144(%rbp), %xmm3
	movsd	-136(%rbp), %xmm2
	movsd	-128(%rbp), %xmm1
	movsd	-120(%rbp), %xmm0
	movq	%rdx, %rsi
	movq	%rax, %rdi
	call	SolveCubic@PLT
	leaq	.LC12(%rip), %rdi
	movl	$0, %eax
	call	printf@PLT
	movl	$0, -44(%rbp)
	jmp	.L8
.L9:
	movl	-44(%rbp), %eax
	cltq
	movsd	-192(%rbp,%rax,8), %xmm0
	leaq	.LC13(%rip), %rdi
	movl	$1, %eax
	call	printf@PLT
	addl	$1, -44(%rbp)
.L8:
	movl	-196(%rbp), %eax
	cmpl	%eax, -44(%rbp)
	jl	.L9
	movl	$10, %edi
	call	putchar@PLT
	movsd	.LC0(%rip), %xmm0
	movsd	%xmm0, -8(%rbp)
	jmp	.L10
.L19:
	movsd	.LC14(%rip), %xmm0
	movsd	%xmm0, -16(%rbp)
	jmp	.L11
.L18:
	movsd	.LC15(%rip), %xmm0
	movsd	%xmm0, -24(%rbp)
	jmp	.L12
.L17:
	movsd	.LC16(%rip), %xmm0
	movsd	%xmm0, -32(%rbp)
	jmp	.L13
.L16:
	leaq	-192(%rbp), %rdx
	leaq	-196(%rbp), %rax
	movsd	-32(%rbp), %xmm3
	movsd	-24(%rbp), %xmm2
	movsd	-16(%rbp), %xmm1
	movsd	-8(%rbp), %xmm0
	movq	%rdx, %rsi
	movq	%rax, %rdi
	call	SolveCubic@PLT
	leaq	.LC12(%rip), %rdi
	movl	$0, %eax
	call	printf@PLT
	movl	$0, -44(%rbp)
	jmp	.L14
.L15:
	movl	-44(%rbp), %eax
	cltq
	movsd	-192(%rbp,%rax,8), %xmm0
	leaq	.LC13(%rip), %rdi
	movl	$1, %eax
	call	printf@PLT
	addl	$1, -44(%rbp)
.L14:
	movl	-196(%rbp), %eax
	cmpl	%eax, -44(%rbp)
	jl	.L15
	movl	$10, %edi
	call	putchar@PLT
	movsd	-32(%rbp), %xmm0
	movsd	.LC0(%rip), %xmm1
	subsd	%xmm1, %xmm0
	movsd	%xmm0, -32(%rbp)
.L13:
	movsd	-32(%rbp), %xmm0
	comisd	.LC17(%rip), %xmm0
	ja	.L16
	movsd	-24(%rbp), %xmm1
	movsd	.LC18(%rip), %xmm0
	addsd	%xmm1, %xmm0
	movsd	%xmm0, -24(%rbp)
.L12:
	movsd	.LC19(%rip), %xmm0
	comisd	-24(%rbp), %xmm0
	ja	.L17
	movsd	-16(%rbp), %xmm0
	movsd	.LC0(%rip), %xmm1
	subsd	%xmm1, %xmm0
	movsd	%xmm0, -16(%rbp)
.L11:
	movsd	-16(%rbp), %xmm0
	pxor	%xmm1, %xmm1
	comisd	%xmm1, %xmm0
	ja	.L18
	movsd	-8(%rbp), %xmm1
	movsd	.LC0(%rip), %xmm0
	addsd	%xmm1, %xmm0
	movsd	%xmm0, -8(%rbp)
.L10:
	movsd	.LC14(%rip), %xmm0
	comisd	-8(%rbp), %xmm0
	ja	.L19
	leaq	.LC21(%rip), %rdi
	call	puts@PLT
	movl	$0, -44(%rbp)
	jmp	.L20
.L21:
	movl	-44(%rbp), %eax
	cltq
	leaq	-204(%rbp), %rdx
	movq	%rdx, %rsi
	movq	%rax, %rdi
	call	usqrt@PLT
	movl	-204(%rbp), %edx
	movl	-44(%rbp), %eax
	movl	%eax, %esi
	leaq	.LC22(%rip), %rdi
	movl	$0, %eax
	call	printf@PLT
	addl	$1, -44(%rbp)
.L20:
	cmpl	$1000, -44(%rbp)
	jle	.L21
	leaq	-204(%rbp), %rdx
	movq	-152(%rbp), %rax
	movq	%rdx, %rsi
	movq	%rax, %rdi
	call	usqrt@PLT
	movl	-204(%rbp), %edx
	movq	-152(%rbp), %rax
	movq	%rax, %rsi
	leaq	.LC23(%rip), %rdi
	movl	$0, %eax
	call	printf@PLT
	leaq	.LC24(%rip), %rdi
	call	puts@PLT
	pxor	%xmm0, %xmm0
	movsd	%xmm0, -40(%rbp)
	jmp	.L22
.L23:
	movsd	-40(%rbp), %xmm1
	movsd	.LC25(%rip), %xmm0
	mulsd	%xmm1, %xmm0
	movsd	.LC26(%rip), %xmm1
	divsd	%xmm1, %xmm0
	movapd	%xmm0, %xmm1
	movsd	-40(%rbp), %xmm0
	leaq	.LC27(%rip), %rdi
	movl	$2, %eax
	call	printf@PLT
	movsd	-40(%rbp), %xmm1
	movsd	.LC0(%rip), %xmm0
	addsd	%xmm1, %xmm0
	movsd	%xmm0, -40(%rbp)
.L22:
	movsd	.LC28(%rip), %xmm0
	comisd	-40(%rbp), %xmm0
	jnb	.L23
	leaq	.LC29(%rip), %rdi
	call	puts@PLT
	pxor	%xmm0, %xmm0
	movsd	%xmm0, -40(%rbp)
	jmp	.L24
.L25:
	movsd	-40(%rbp), %xmm1
	movsd	.LC26(%rip), %xmm0
	mulsd	%xmm1, %xmm0
	movsd	.LC25(%rip), %xmm1
	divsd	%xmm1, %xmm0
	movapd	%xmm0, %xmm1
	movsd	-40(%rbp), %xmm0
	leaq	.LC30(%rip), %rdi
	movl	$2, %eax
	call	printf@PLT
	movsd	-40(%rbp), %xmm1
	movsd	.LC31(%rip), %xmm0
	addsd	%xmm1, %xmm0
	movsd	%xmm0, -40(%rbp)
.L24:
	movsd	.LC32(%rip), %xmm0
	comisd	-40(%rbp), %xmm0
	jnb	.L25
	movl	$0, %eax
	leave
	.cfi_def_cfa 7, 8
	ret
	.cfi_endproc
.LFE5:
	.size	main, .-main
	.section	.rodata
	.align 8
.LC0:
	.long	0
	.long	1072693248
	.align 8
.LC1:
	.long	0
	.long	-1071316992
	.align 8
.LC2:
	.long	0
	.long	1077936128
	.align 8
.LC3:
	.long	0
	.long	-1069678592
	.align 8
.LC4:
	.long	0
	.long	-1072562176
	.align 8
.LC5:
	.long	0
	.long	1076953088
	.align 8
.LC6:
	.long	0
	.long	-1072955392
	.align 8
.LC7:
	.long	0
	.long	1077280768
	.align 8
.LC8:
	.long	0
	.long	-1069613056
	.align 8
.LC9:
	.long	1717986918
	.long	-1070897562
	.align 8
.LC10:
	.long	0
	.long	-1069449216
	.align 8
.LC14:
	.long	0
	.long	1076101120
	.align 8
.LC15:
	.long	0
	.long	1075052544
	.align 8
.LC16:
	.long	0
	.long	-1074790400
	.align 8
.LC17:
	.long	0
	.long	-1071251456
	.align 8
.LC18:
	.long	0
	.long	1071644672
	.align 8
.LC19:
	.long	0
	.long	1076756480
	.align 8
.LC25:
	.long	1413754136
	.long	1074340347
	.align 8
.LC26:
	.long	0
	.long	1080459264
	.align 8
.LC28:
	.long	0
	.long	1081507840
	.align 8
.LC31:
	.long	2723323193
	.long	1066524486
	.align 8
.LC32:
	.long	2539654043
	.long	1075388923
	.ident	"GCC: (Debian 8.1.0-12) 8.1.0"
	.section	.note.GNU-stack,"",@progbits
