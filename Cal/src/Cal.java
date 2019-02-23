import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;


public class Cal
{

	public static void main(String args[])
	{
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter starting number: ");
		int start = sc.nextInt();

		System.out.print("Enter available moves: ");
		int moves = sc.nextInt();

		System.out.print("Enter goal number: ");
		int goal = sc.nextInt();
		//System.out.println();

		System.out.print("Enter all possible moves separated by a space: ");
		sc.nextLine();
		String poss = sc.nextLine();
		System.out.println();


		ArrayList<String> possible = new ArrayList<String>();
		int startTemp = start;
		int answer = 0;

		while(poss != "")
		{
			int index = poss.indexOf(" ");
			if(index == -1)
			{
				possible.add(poss);
				break;
			}
			possible.add(poss.substring(0, index));
			poss = poss.substring(index+1);
		}

		int[] list = createList(moves, possible.size());

		outerLoop:
		for(int j=0;j<list.length;j++)
		{
			int test = list[j];

			while(test > 0)
			{
				String chosen = possible.get((test%10)-1);
				char op = chosen.charAt(0);

				switch (op)
				{
				case '<':
					startTemp /= 10;
					break;
				case 'r':
					startTemp = reverse(startTemp);
					break;
				case 'p':
					startTemp *= -1;
					break;
				case '+':
					startTemp += Integer.parseInt(chosen.substring(1));
					break;
				case '-':
					startTemp -= Integer.parseInt(chosen.substring(1));
					break;
				case 'x':
					startTemp *= Integer.parseInt(chosen.substring(1));
					break;
				case '/':
					if(startTemp % Integer.parseInt(chosen.substring(1)) != 0)
					{
						startTemp = start;
						continue outerLoop;
					}
					startTemp /= Integer.parseInt(chosen.substring(1));
					break;
				case 'j':
					try{
						startTemp = Integer.parseInt(""+Integer.toString(startTemp) + chosen.substring(1));
					}catch(Exception e){
						startTemp = start;
						continue outerLoop;
					}
					break;
				case '^':
					startTemp = (int) Math.pow(startTemp, Integer.parseInt(chosen.substring(1)));
					break;
				case 'c':
					String thisTo = chosen.substring(1, chosen.indexOf("-"));
					String that = chosen.substring(chosen.indexOf("-")+1);
					String temp = Integer.toString(startTemp);
					temp = temp.replace(thisTo, that);
					startTemp = Integer.parseInt(temp);
					break;
				case 's':
					startTemp = sum(startTemp);
					break;
				}
				test /= 10;
			}

			if(startTemp == goal)
			{
				answer = list[j];
				break;
			}
			startTemp = start;
		}

		while(answer > 0)
		{
			System.out.print(possible.get((answer%10) - 1) + "  ");
			answer /= 10;
		}
		System.out.println();


	}




	public static int[] createList(int moves, int poss)
	{
		int list[] = new int[(int) Math.pow(poss, moves)];
		Arrays.fill(list, 0);

		int x = 0;

		for(int j=0;j<moves;j++)
		{
			x *= 10;
			x += 1;
		}


		for(int i=0;i<list.length;i++)
		{
			list[i] = x;
			x++;

			while (String.valueOf(x).contains(String.valueOf(poss+1)))
			{
				int temp = x;
				int place = 1;
				while(temp > 0)
				{
					if(temp % 10 == poss+1)
						break;
					place *= 10;
					temp /= 10;
				}

				int part1 = x/(place*10);
				int part2 = x%(place*10);
				part1++;
				part2 = (part2 % place) + place;

				x = part1 * place*10 + part2;
			}


		}
		return list;
	}

	public static int reverse(int x) {
	    int reversedNum = 0;
	    int input = x;

	    while (input != 0) {
	        reversedNum = reversedNum * 10 + input % 10;
	        input = input / 10;
	    }

	    return (int) reversedNum;
	}

	public static int sum(int x)
	{
		int answer = 0;
		while(x != 0)
		{
			answer += x%10;
			x /= 10;
		}
		return answer;
	}

}
