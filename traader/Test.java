class Test{


	public static void main(String[] args){

		int[] arr = {1, 1, -3, 4, 45, 6, 52, 2, 2, 11};

		System.out.println("----- Factorial av 5 -----");
		System.out.println("> " + factorial(5) + " ("+120+")\n");
		
		System.out.println("----- Factorial(iterativ) av 5 -----");
		System.out.println("> " + factorialIterative(5) + " ("+120+")\n");
		
		System.out.print("----- 10 forste fibonacci tall -----\n> ");
		for(int i = 1; i<11;i++) System.out.print(fibonacci(i) + " ");
		System.out.println("\n");
		
		System.out.println("----- Print int array -----");
		print(arr);
		System.out.println("\n");
		
		System.out.println("----- Oppgave 1: Alle tall fra 0 til  10 -----");
		System.out.print("> ");
		print(10);
		System.out.println("\n");
		
		System.out.println("----- Oppgave 2: Storste verdi i array -----");
		System.out.println("> " + largest(arr) + "\n");
		
		System.out.println("----- Oppgave 3: Beregn 7^3 -----");
		System.out.println("> " + pow(7, 3) + " ("+343+")\n");
		
		System.out.println("----- Oppgave 4: Beregn 7^(-3) -----");
		System.out.println("> " + powNeg(7, -3) + "\n ("+0.00291545189+")\n");
		
		System.out.println("----- Oppgave 5: isPalindrome() -----");
		System.out.println("> \"agnes i senga\": " + isPalindrome("agnes i senga"));
		System.out.println("> \"racecar\": " + isPalindrome("racecar"));
		System.out.println("> \"Hallo alle sammen\": " + isPalindrome("Hallo alle sammen"));
		System.out.println("> \"Agnes i senga\": " + isPalindrome("Agnes i senga"));
		System.out.println();

	}


	// EKSEMPEL 1: FACTORIAL
	public static int factorial(int n){
		if(n == 2) return 2;
		//if(n == 0) return 1;
		return n*factorial(n-1);
	}

	// EKSEMPEL 1.b Iterativ versjon
	public static int factorialIterative(int n){
		int prod = 1;
		
		for(; n>=1; n--)
			prod = prod*n;

		return prod;
	}

	// EKSEMPEL 2: FIBONACCI TALL #n
	public static int fibonacci(int n){
		if(n == 1 || n == 2) return 1;
		return fibonacci(n-1) + fibonacci(n-2);
	}

	// EKSEMPEL 3: PRINT ARRAY
	public static void print(int[] arr){
		print(arr, 0);
	}
	public static void print(int[] arr, int i){
		if(i>=arr.length || i < 0) return;
		
		System.out.print(arr[i] + " ");
		print(arr, i+1);
	}


	// Oppgave 1: Lag en rekursiv metode som skriver ut alle tallene
	//            opp til n

	// Løsning:
	public static void print(int n){
		if(n<0) return;
		print(n-1);
		System.out.print(n + " ");
	}

	// OPPGAVE 2: Skriv en rekursiv metode som hente største tall
	//            i et int[]

	// Løsning:
	public static int largest(int[] arr){
		return largest(arr, arr[0], 1);
	}
	public static int largest(int[] arr, int largest, int i){
		if(i>= arr.length) return largest;
		
		if(arr[i] > largest) largest = arr[i];
		
		return largest(arr, largest, i+1);
	}

	// OPPGAVE 3: Skriv en rekursiv metode som beregner n^POW

	// Løsning:
	public static int pow(int fac, int pow){
		if(pow == 1) return fac;
		return fac*pow(fac, pow-1);
	}

	// OPPGAVE 4: Utvid pow til å ta hånd om negative eksponenter
	// Tips: x^(-2) = 1/x^(2)
	public static double powNeg(int fac, int pow){
		if(pow == 1) return fac;
		if(pow < 0 ) return 1/(double)(fac*powNeg(fac, (-1*pow)-1));
		return fac*powNeg(fac, pow-1);
	}

	// Oppgave 5: Skriv en metode som sjekker om en string s
	//            er et palindrom eller ikke
	// Tips: String har metodene:
	//       .length()
	//       .charAt(int index)
	//       .substring(int start(inclusive), int stop(exclusive))
	// Løsning:
	public static boolean isPalindrome(String s){
		//System.out.println(s);
		if(s.length() == 1 || s.length() == 0) return true;
		
		if(s.charAt(0) != s.charAt(s.length()-1)) return false;
		
		return isPalindrome(s.substring(1, s.length()-1));
	}

}
